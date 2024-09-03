// implements userservice interface with logic for user registration, confirmation, and interaction with user repo

package com.dissertation.WritingApp.service.impl;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.dtos.UserDto;
import com.dissertation.WritingApp.models.EmailConfirmationToken;
import com.dissertation.WritingApp.repositories.EmailConfirmationTokenRepository;
import com.dissertation.WritingApp.repositories.UserRepository;
import com.dissertation.WritingApp.service.EmailService;
import com.dissertation.WritingApp.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{
	
	 private final PasswordEncoder passwordEncoder;
	 private final UserRepository userRepository;
	 private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;
	 private final JavaMailSender mailSender;
	 private final EmailService emailService;
	 
	 @Autowired
	 public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender, EmailConfirmationTokenRepository emailConfirmationTokenRepository, EmailService emailService) {
	  this.userRepository = userRepository;
	  this.passwordEncoder = passwordEncoder;
	  this.mailSender = mailSender;
	  this.emailConfirmationTokenRepository = emailConfirmationTokenRepository;
	  this.emailService = emailService;
	 }

	 @Override
	 public User findByUsername(String username) {
	  return userRepository.findUserByUsername(username);
	 }

//	 @Override
//	 public User save(UserDto userDto) {
//	  User user = new User(
//			  userDto.getUserId(),
//			  userDto.getUsername(),
//			  passwordEncoder.encode(userDto.getPassword()),
//			  userDto.getFullname(),
//			  userDto.getEmail(),
//			  userDto.isEmailVerified(),
//			  userDto.getVerificationToken());
//	  return userRepository.save(user);
//	 }
//	 
//	 
//	 public void registerUser(UserDto userDto) {
//		    // Create a new User object from UserDto
//		    User user = new User();
//		    user.setUsername(userDto.getUsername());
//		    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		    user.setFullname(userDto.getFullname());
//		    user.setEmail(userDto.getEmail());
//		    user.setEmailVerified(false); // Default to false
//		    user.setVerificationToken(UUID.randomUUID().toString()); // Generate a token
//
//		    // Save the user to the database
//		    userRepository.save(user);
//
//		    // Send confirmation email
//		    sendConfirmationEmail(user);
//		}
	 
//	 @Override
	 public User save(UserDto userDto) {
	  User user = new User(
			  userDto.getUserId(),
			  userDto.getUsername(),
			  passwordEncoder.encode(userDto.getPassword()),
			  userDto.getFullname(),
			  userDto.getEmail(),
			  false,
			  UUID.randomUUID().toString());
	  return userRepository.save(user);
	 }
	 
	 
	 public void registerUser(UserDto userDto) {
	        // Convert UserDto to User entity and set additional fields
	        User user = new User(
	            null,
	            userDto.getUsername(),
	            passwordEncoder.encode(userDto.getPassword()),
	            userDto.getFullname(),
	            userDto.getEmail(),
	            false, // Email is not verified
	            UUID.randomUUID().toString() // Generate a verification token
	        );
	        // Save the user to the database
	        userRepository.save(user);

	        // Send confirmation email
	        sendConfirmationEmail(user);
	    }

	    private void sendConfirmationEmail(User user) {
	       // String token = UUID.randomUUID().toString(); // Generate a random confirmation token
	        
	        // Save token in the database
	        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
	        emailConfirmationToken.setToken(user.getVerificationToken());
	        emailConfirmationToken.setUser(user);
	        emailConfirmationToken.setTimeStamp(LocalDateTime.now());
	        emailConfirmationToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // Token validity for 24 hours
	        emailConfirmationTokenRepository.save(emailConfirmationToken);

	        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + user.getVerificationToken();

	        // Use EmailService to send the email
	        emailService.sendConfirmationEmail(user.getEmail(), confirmationUrl);
	    }

	    
	    public Boolean confirmUser(String token) {
	        // Look up the user by the token in a real application
	        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token);
	        
	        if (emailConfirmationToken == null || emailConfirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
	            // Invalid or expired token
	            return false;
	        }
	        
	        User user = emailConfirmationToken.getUser();
	        user.setEmailVerified(true); // Activate the user account
	        userRepository.save(user);

	        // Token is used, so mark it as confirmed
	        emailConfirmationToken.setConfirmedAt(LocalDateTime.now());
	        emailConfirmationTokenRepository.save(emailConfirmationToken);
	        
	        return true;
	    }
	    
	    public Boolean verifyEmail(String token) {
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