package com.dissertation.WritingApp.service;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.dtos.UserDto;


public interface UserService {
	User findByUsername(String username);
	
	User save(UserDto userDto);
}
