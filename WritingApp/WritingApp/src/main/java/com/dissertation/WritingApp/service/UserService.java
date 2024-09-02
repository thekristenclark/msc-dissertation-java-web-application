package com.dissertation.WritingApp.service;

import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;


import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.dtos.UserDto;

@Service
public interface UserService {
	User findByUsername(String username);
	
	User save(UserDto userDto);
	
    void registerUser(User user);
    boolean confirmUser(String token);
    
    
	
}
