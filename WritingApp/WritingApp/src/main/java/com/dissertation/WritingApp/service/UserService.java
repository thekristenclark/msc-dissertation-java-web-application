// manages user registration and email verification

package com.dissertation.WritingApp.service;

import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.User;

@Service
public interface UserService {
	User findByUsername(String username);
	
    void registerUser(User user);
    
    boolean verifyEmail(String token);
    
}
