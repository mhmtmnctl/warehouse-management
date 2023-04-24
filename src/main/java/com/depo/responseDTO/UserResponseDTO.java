package com.depo.responseDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.depo.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

	private Long id;

	private String first_name;

	private String last_name;

	private String phone;

	private String email;

	private byte status;

	private LocalDate create_at;

	private LocalDate update_at;
		
	private Set<String> roles;
	
	
	public void setRoles(Set<Role> roles) {
		Set<String> roleStr=new HashSet<>();
		
		roles.forEach(r->{
			roleStr.add(r.getType().getName());
		});
		
		this.roles=roleStr;
	}
}