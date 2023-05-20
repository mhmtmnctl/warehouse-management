package com.depo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.depo.domain.User;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserResponseDTO userToUserResponseDTO(User user);
	
	@Mapping(target="create_at", ignore=true)
	@Mapping(target="update_at", ignore=true)
	@Mapping(target="id", ignore=true)
	@Mapping(target="built_in", ignore=true)
	@Mapping(target="roles", ignore=true)	
	User userRequestDTOToUser(UserRequestDTO userRequestDTO);
	
	List<UserResponseDTO> map(List<User> userList);
}