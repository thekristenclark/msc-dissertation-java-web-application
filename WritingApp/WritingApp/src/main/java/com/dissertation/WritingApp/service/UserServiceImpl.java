package com.dissertation.WritingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.dtos.UserDto;
import com.dissertation.WritingApp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	 @Autowired
	 PasswordEncoder passwordEncoder;

	 private UserRepository userRepository;

	 public UserServiceImpl(UserRepository userRepository) {
	  super();
	  this.userRepository = userRepository;
	 }

	 @Override
	 public User findByUsername(String username) {
	  return userRepository.findUserByUsername(username);
	 }

	 @Override
	 public User save(UserDto userDto) {
	  User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),userDto.getFullname());
	  return userRepository.save(user);
	 }


/*@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

	public AuthenticationResponse register(RegisterRequest request) {
	        String encodedPassword = passwordEncoder.encode(request.getPassword());
	        var user = User.builder()
	                .firstname(request.getFirstname())
	                .lastname(request.getLastname())
	                .email(request.getEmail())
	                .password(encodedPassword)
	                .isEnabled(true)
	                .isAccountNonLocked(true)
	                .isCredentialsNonExpired(true)
	                .isAccountNonExpired(true)
	                .build();
	        var savedUser = userRepository.save(user);
	        return "User registered successfully";
	    }
*/
}