package com.depo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.depo.domain.Role;
import com.depo.domain.User;
import com.depo.enums.RoleType;
import com.depo.exception.BuiltInException;
import com.depo.exception.ConflictException;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.UserMapper;
import com.depo.repository.UserRepository;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;
import com.depo.security.SecurityUtils;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	public UserService(UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder,
			UserMapper userMapper) {

		this.userRepository = userRepository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;

	}

	public User getCurrentUser() {

		String email = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRINCIPAL_FOUND_MESSAGE));
		User user = getUserByEmail(email);
		return user;

	}

	public User getUserByEmail(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MESSAGE));
		return user;
	}

	// ============= Register User============================
	public UserResponseDTO saveUser(UserRequestDTO registerRequest) {

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new ConflictException(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE);
		}

		Role role = roleService.findByType(RoleType.ROLE_CUSTOMER);
		Set<Role> roles = new HashSet<>();
		roles.add(role);

		String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

		User user = userMapper.userRequestDTOToUser(registerRequest);

		user.setStatus((byte) 0);
		user.setCreate_at(LocalDate.now());
		user.setRoles(roles);
		user.setBuilt_in(false);
		user.setPassword(encodedPassword);
		userRepository.save(user);

		UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

		return userResponseDTO;

	}

	// ========= GET USER BY ID - ADMIN ======================
	public UserResponseDTO getUserByIdAdmin(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MESSAGE));

		UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

		return userResponseDTO;
	}

	// ========= UPDATE AUTH USER ======================
	public UserResponseDTO updateAuthUser(UserRequestDTO userRequestDTO) {

		User user = getCurrentUser();
		
		if (user.getBuilt_in()) {
			throw new BuiltInException(ErrorMessage.BUILTIN_MESSAGE);
		}
			user.setEmail(userRequestDTO.getEmail());
	        user.setFirst_name(userRequestDTO.getFirst_name());
	        user.setLast_name(userRequestDTO.getLast_name());
	        user.setPhone(userRequestDTO.getPhone());
	        user.setUpdate_at(LocalDate.now());
		
	
		userRepository.save(user);

		UserResponseDTO dto = userMapper.userToUserResponseDTO(user);

		return dto;
	}

	// ========= DELETE USER ID AUTH ======================
	public void deleteAuthUserById() {
		User user = getCurrentUser();

		if (user.getBuilt_in()) {
			throw new BuiltInException(ErrorMessage.BUILTIN_MESSAGE);
		}

		userRepository.delete(user);

	}

}
