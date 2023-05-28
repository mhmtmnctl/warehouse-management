package com.depo.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.depo.controller.UserController;
import com.depo.responseDTO.UserResponseDTO;

public class TestUserController {
	

	 @Mock
	    private UserServiceImpl userService;

	    @InjectMocks
	    private UserController userController;

	    @Test
	    public void testGetUserByIdAdmin() {
	    	
	        // Mock userService.getUserByIdAdmin method
	        Long userId = 1L;
	        UserResponseDTO mockResponseDTO = new UserResponseDTO();
	        when(userService.getUserByIdAdmin(userId)).thenReturn(mockResponseDTO);

	        // Call getUserByIdAdmin method and verify the result
	        ResponseEntity<UserResponseDTO> response = userController.getUserByIdAdmin(userId);	       
	       // assertEquals(mockResponseDTO, response.getBody());
	    }

}
