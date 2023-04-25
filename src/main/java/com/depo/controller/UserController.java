package com.depo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depo.requestDTO.LoginRequestDTO;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.LoginResponseDTO;
import com.depo.responseDTO.UserResponseDTO;
import com.depo.security.jwt.JwtUtils;
import com.depo.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

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
}
