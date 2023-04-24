package com.depo.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.depo.domain.Role;
import com.depo.domain.User;
import com.depo.enums.RoleType;
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

	// ============= Create User============================
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
}
