package com.JWT.tutorial.JwtTurorial.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoToUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7018713661832693174L;
	private String username;
	private String password;
	
	

	public UserInfoToUserDetails(com.JWT.tutorial.JwtTurorial.Userdetails.UserDetails userDetails) {
		super();
		this.username = userDetails.getUsername();
		this.password = userDetails.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
