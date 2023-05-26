package com.depo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depo.domain.Role;
import com.depo.enums.RoleType;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.repository.RoleRepository;
import com.depo.service.ServiceInterfaces.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByType(RoleType roleType) {

		return roleRepository.findByType(roleType)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND_MESSAGE));
	}

}