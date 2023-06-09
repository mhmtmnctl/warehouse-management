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
import com.depo.exception.BuiltInException;
import com.depo.exception.ConflictException;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.UserMapper;
import com.depo.repository.UserRepository;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;
import com.depo.security.SecurityUtils;
import com.depo.service.ServiceInterfaces.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleServiceImpl roleService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, @Lazy PasswordEncoder passwordEncoder,
			UserMapper userMapper) {

		this.userRepository = userRepository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;

	}
	@Override
	public User getCurrentUser() {

		String email = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRINCIPAL_FOUND_MESSAGE));
		User user = getUserByEmail(email);
		return user;

	}
	@Override
	public User getUserByEmail(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MESSAGE));
		return user;
	}

	// ============= Register User============================
	@Override
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
		user.setBuiltIn(false);
		user.setPassword(encodedPassword);
		userRepository.save(user);

		UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

		return userResponseDTO;

	}

	// ========= GET USER BY ID - ADMIN ======================
	@Override
	public UserResponseDTO getUserByIdAdmin(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MESSAGE));

		UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

		return userResponseDTO;
	}

	// ========= UPDATE AUTH USER ======================
	@Override
	public UserResponseDTO updateAuthUser(UserRequestDTO userRequestDTO) {

		User user = getCurrentUser();
		
		if (user.getBuiltIn()) {
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
	@Override
	public void deleteAuthUserById() {
		User user = getCurrentUser();

		if (user.getBuiltIn()) {
			throw new BuiltInException(ErrorMessage.BUILTIN_MESSAGE);
		}

		userRepository.delete(user);

	}

}
