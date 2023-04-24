package com.depo.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.depo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {	
private static final long serialVersionUID = 1L;	
	
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImpl build(User user) {		
		List<SimpleGrantedAuthority> authorities= user.getRoles().stream().map(role->
				new SimpleGrantedAuthority(role.getType().name())).collect(Collectors.toList());		
		return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	}