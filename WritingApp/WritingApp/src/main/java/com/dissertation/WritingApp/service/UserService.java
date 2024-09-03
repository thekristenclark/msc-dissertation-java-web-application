// manages user registration and email verification

package com.dissertation.WritingApp.service;

import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;


import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.dtos.UserDto;

@Service
public interface UserService {
	User findByUsername(String username);
	
	User save(UserDto userDto);
	
    void registerUser(UserDto userDto);
    Boolean confirmUser(String token);
    
//    boolean verifyEmail(String token);

    
//    boolean verifyTotp(final String code,String username);

//    void sendRegistrationConfirmationEmail(final User user) throws MessagingException;
 //   Boolean verifyUser(final String token) throws InvalidTokenException; 
    
}
