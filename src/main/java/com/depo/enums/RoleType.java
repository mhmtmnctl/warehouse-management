package com.depo.enums;

public enum RoleType {
	ROLE_CUSTOMER("Customer"),
	ROLE_ADMIN("Administrator");
	private String name;
	
	private RoleType(String name) {
		this.name=name;
	}	
	public String getName() {
		return name;
	}	
}