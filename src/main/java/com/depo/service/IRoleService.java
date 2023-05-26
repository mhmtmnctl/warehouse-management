package com.depo.service;

import com.depo.domain.Role;
import com.depo.enums.RoleType;

public interface IRoleService {
	
	Role findByType(RoleType roleType);

}
