package com.depo.service.ServiceInterfaces;

import com.depo.domain.User;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;

public interface IUserService {

	User getCurrentUser();

	User getUserByEmail(String email);
	UserResponseDTO saveUser(UserRequestDTO registerRequest);
	UserResponseDTO getUserByIdAdmin(Long userId) ;
	UserResponseDTO updateAuthUser(UserRequestDTO userRequestDTO) ;
	void deleteAuthUserById();
	
}
