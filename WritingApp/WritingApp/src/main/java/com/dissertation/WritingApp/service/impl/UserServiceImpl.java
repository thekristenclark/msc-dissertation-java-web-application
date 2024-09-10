// implements userservice interface with logic for user registration, confirmation, and interaction with user repo

package com.dissertation.WritingApp.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.User;
//import com.dissertation.WritingApp.dtos.UserDto;
import com.dissertation.WritingApp.repositories.UserRepository;
import com.dissertation.WritingApp.service.EmailService;
import com.dissertation.WritingApp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	 private final PasswordEncoder passwordEncoder;
	 private final UserRepository userRepository;
	 private final EmailService emailService;
	 
	 @Autowired
	 public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
	  this.userRepository = userRepository;
	  this.passwordEncoder = passwordEncoder;
	  this.emailService = emailService;
	 }

	 @Override
	 public User findByUsername(String username) {
	  return userRepository.findUserByUsername(username);
	 }
	 
	 @Override
	 public void registerUser(User user) {
	        User newUser = new User();
	        newUser.setUsername(user.getUsername());
	        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        newUser.setFullname(user.getFullname());
	        newUser.setEmail(user.getEmail());
	        newUser.setEmailVerified(false); // Default to false

	        String verificationToken = UUID.randomUUID().toString();
	        newUser.setVerificationToken(verificationToken);
	        
	        userRepository.save(newUser);	// saves the token for token storage
	        sendConfirmationEmail(newUser.getEmail(), verificationToken);
	    }

	    private void sendConfirmationEmail(String email, String token) {
	    	String confirmationUrl = "http://localhost:8080/email-confirmation?token=" + token;
	    	String subject = "Email Verification";
	    	String body = "<html><body>"
	    	        + "<p>We are excited to have you get started! Please verify your email "
	    	        + "by clicking on the following link:</p>"
	    	        + "<p><a href=\"" + confirmationUrl + "\">Verify your email</a></p>"
	    	        + "<p>Regards,<br>Registration Team</p>"
	    	        + "</body></html>";
	    	emailService.sendConfirmationEmail(email,  subject, body);	
	    }
	    
	    public boolean verifyEmail(String token) {
	        User user = userRepository.findByVerificationToken(token);
	        if (user != null && !user.isEmailVerified()) {
	            user.setEmailVerified(true);
	            user.setVerificationToken(null);
	            userRepository.save(user);
	            return true;
	        }
	        return false;
	    }

	
}