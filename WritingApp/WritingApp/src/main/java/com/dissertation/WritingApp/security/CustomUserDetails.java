package com.dissertation.WritingApp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	 private String username;
	 private String password;
	 private Collection<? extends GrantedAuthority> authorities;
	 private String fullname;
	 private Boolean emailVerified;

	 
	 public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String fullname, Boolean emailVerified) {
		 this.username = username;
		 this.password = password;
		 this.authorities = authorities;
		 this.fullname = fullname;
		 this.emailVerified = emailVerified;
	}

	public String getFullname() {
		return fullname;
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return emailVerified;	// only enable account if email is verified
	}
}