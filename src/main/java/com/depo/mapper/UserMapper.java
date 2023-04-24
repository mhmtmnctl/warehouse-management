package com.depo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.depo.domain.User;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserResponseDTO userToUserResponseDTO(User user);
	
	User userRequestDTOToUser(UserRequestDTO userRequestDTO);
	
	List<UserResponseDTO> map(List<User> userList);
}