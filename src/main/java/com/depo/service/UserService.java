package com.depo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depo.domain.User;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.repository.UserRepository;
import com.depo.security.SecurityUtils;




@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	   public User getCurrentUser() {

	        String email = SecurityUtils.getCurrentUserLogin()
	                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRINCIPAL_FOUND_MESSAGE));
	        User user = getUserByEmail(email);
	        return user;

	    }

	    public User getUserByEmail(String email) {

	        User user = userRepository.findByEmail(email).orElseThrow(
	                () -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MESSAGE));
	        return user;
	    }
}
