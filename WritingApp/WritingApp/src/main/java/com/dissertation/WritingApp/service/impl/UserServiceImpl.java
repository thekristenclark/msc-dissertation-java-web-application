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
import com.dissertation.WritingApp.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{
	
	 @Autowired
	 PasswordEncoder passwordEncoder;

	 private UserRepository userRepository;
	 private EmailConfirmationTokenRepository emailConfirmationTokenRepository;
	 
	 private JavaMailSender mailSender;
	 

	 public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender, EmailConfirmationTokenRepository emailConfirmationTokenRepository) {
	  super();
	  this.userRepository = userRepository;
	  this.mailSender = mailSender;
	  this.emailConfirmationTokenRepository = emailConfirmationTokenRepository;
	  this.passwordEncoder = passwordEncoder;
	 }

	 @Override
	 public User findByUsername(String username) {
	  return userRepository.findUserByUsername(username);
	 }

//	 @Override
	 public User save(UserDto userDto) {
	  User user = new User(userDto.getUserId(), userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),userDto.getFullname(), userDto.getEmail());
	  return userRepository.save(user);
	 }
	 
	 
	    @Override
	    public void registerUser(User user) {
	        user.setEnabled(false); // User is not enabled until they confirm their email
	        userRepository.save(user);

	        // Send confirmation email
	        sendConfirmationEmail(user);
	    }

	    private void sendConfirmationEmail(User user) {
	        String token = UUID.randomUUID().toString(); // Generate a random confirmation token
	        
	        // Save token in the database
	        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
	        emailConfirmationToken.setToken(token);
	        emailConfirmationToken.setUser(user);
	        emailConfirmationToken.setTimeStamp(LocalDateTime.now());
	        emailConfirmationToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // Token validity for 24 hours
	        emailConfirmationTokenRepository.save(emailConfirmationToken);

	        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + token;

	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setTo(user.getEmail());
	            helper.setText("Click the link to confirm your registration: " + confirmationUrl);
	            helper.setSubject("Registration Confirmation");
	            helper.setFrom(new InternetAddress("noreply@yourdomain.com"));
	            mailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }

	        // In a real implementation, save the token associated with the user
	    }

	    @Override
	    public boolean confirmUser(String token) {
	        // Look up the user by the token in a real application
	        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token);
	        
	        if (emailConfirmationToken == null || emailConfirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
	            // Invalid or expired token
	            return false;
	        }
	        
	        User user = emailConfirmationToken.getUser();
	        user.setEnabled(true); // Activate the user account
	        userRepository.save(user);

	        // Token is used, so mark it as confirmed
	        emailConfirmationToken.setConfirmedAt(LocalDateTime.now());
	        emailConfirmationTokenRepository.save(emailConfirmationToken);
	        
	        return true;
	    }

	
}