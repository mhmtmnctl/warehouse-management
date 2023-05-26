package com.depo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.depo.requestDTO.LoginRequestDTO;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.LoginResponseDTO;
import com.depo.responseDTO.UserResponseDTO;
import com.depo.security.jwt.JwtUtils;
import com.depo.service.UserServiceImpl;




@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

// ========= REGISTER USER ======================
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO registerRequest) {
		UserResponseDTO response= userService.saveUser(registerRequest);
		
		return ResponseEntity.ok(response);
	}

// ========= LOGIN USER ======================
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwtToken = jwtUtils.generateJwtToken(userDetails);
		LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
	// ========= GET USER BY ID ======================
	@GetMapping("/{id}/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDTO> getUserByIdAdmin(@PathVariable("id") Long userId) {
		UserResponseDTO usersDTO = userService.getUserByIdAdmin(userId);
		return ResponseEntity.ok(usersDTO);
	}
	
	// ========= UPDATE USER ======================
		@PutMapping("/update/auth")
		@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
		public ResponseEntity<UserResponseDTO> updateAuthUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

			UserResponseDTO dto = userService.updateAuthUser(userRequestDTO);
			return ResponseEntity.ok(dto);
		}
		
		// ========= DELETE USER ID AUTH ======================
		@DeleteMapping("/delete/auth")
		@PreAuthorize("hasRole('ADMIN')  or  hasRole('CUSTOMER')")
		public ResponseEntity<String> deleteUserIdByAuth() {
			userService.deleteAuthUserById();
			
			return ResponseEntity.ok("Succesfully deleted");
		}
	
}
