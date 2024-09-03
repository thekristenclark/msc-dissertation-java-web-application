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
//	 public User save(UserDto userDto) {
//	  User user = new User(
//			  userDto.getUserId(),
//			  userDto.getUsername(),
//			  passwordEncoder.encode(userDto.getPassword()),
//			  userDto.getFullname(),
//			  userDto.getEmail(),
//			  userDto.isEmailVerified(),
//			  userDto.getVerificationToken()
//			  );
//	  return userRepository.save(user);
//	 }
	 
	 @Override
	 public void registerUser(UserDto userDto) {
	        User newUser = new User();
	        newUser.setUsername(userDto.getUsername());
	        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
	        newUser.setFullname(userDto.getFullname());
	        newUser.setEmail(userDto.getEmail());
	        newUser.setEmailVerified(false); // Default to false
	//        newUser.setVerificationToken(UUID.randomUUID().toString()); // Generate a token

	        String verificationToken = UUID.randomUUID().toString();
	        newUser.setVerificationToken(verificationToken);
	        
	        userRepository.save(newUser);		// saves the token for token storage
	        sendConfirmationEmail(newUser.getEmail(), verificationToken);
	    }

	    private void sendConfirmationEmail(String email, String token) {
//	        String token = UUID.randomUUID().toString(); // Generate a random confirmation token
//	        System.out.println("Generated Token: " + token);
//	        
//	        // Save token in the database
//	        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
//	        emailConfirmationToken.setToken(token);
//	        emailConfirmationToken.setUser(user);
//	        emailConfirmationToken.setTimeStamp(LocalDateTime.now());
//	        emailConfirmationToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // Token validity for 24 hours
//	        emailConfirmationTokenRepository.save(emailConfirmationToken);
//
//	        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + token;
//	        System.out.println("Confirmation Url: " + confirmationUrl);
//
//	        // Use EmailService to send the email
//	        emailService.sendConfirmationEmail(user.getEmail(), confirmationUrl);
	    	String confirmationUrl = "http://localhost:8080/email-confirmation?token=" + token;
	    	String subject = "Email Verification";
	    	String body = "<html><body>"
	    	        + "<p>We are excited to have you get started! Please verify your email by clicking on the following link:</p>"
	    	        + "<p><a href=\"" + confirmationUrl + "\">Verify your email</a></p>"
	    	        + "<p>Regards,<br>Registration Team</p>"
	    	        + "</body></html>";
	    	emailService.sendConfirmationEmail(email,  subject, body);
	    	
	    }

	    
	    public Boolean verifyUser(String token) {
	        // Look up the user by the token in a real application
	    	System.out.println("Token received: " + token);
	        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token);
	        
	        if (emailConfirmationToken == null || emailConfirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
	            System.out.println("No token found for: " + token); // Debug log
	            // Invalid or expired token
	            return false;
	        }
//	        if (emailConfirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
//	            System.out.println("Token expired for: " + token); // Debug log
//	            return false;
//	        }
	        
//	        User user = emailConfirmationToken.getUser();
//	        if (user != null) {
//	            user.setEmailVerified(true); // Activate the user account
//	            userRepository.save(user); // Save the updated user
//	            System.out.println("User email verified: " + user.getUsername()); // Debug log
//	        } else {
//	            System.out.println("User not found for token: " + token); // Debug log
//	            return false;
//	        }
//
//
//	        // Token is used, so mark it as confirmed
//	        emailConfirmationToken.setConfirmedAt(LocalDateTime.now());
//	        emailConfirmationTokenRepository.save(emailConfirmationToken);
//	      
//	        System.out.println("Email confirmation token confirmed: " + token); // Debug log
//	        return true;
	        User user = emailConfirmationToken.getUser();
	        user.setEmailVerified(true);
	        userRepository.save(user);
//	        emailConfirmationToken.setConfirmedAt(LocalDateTime.now());
//	        emailConfirmationTokenRepository.save(emailConfirmationToken);
//	        emailConfirmationTokenRepository.delete(emailConfirmationToken);	// optional delete token after verification
	        return true;
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