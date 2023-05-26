package com.depo.service.ServiceInterfaces;

import com.depo.domain.Role;
import com.depo.enums.RoleType;

public interface IRoleService {
	
	Role findByType(RoleType roleType);

}
