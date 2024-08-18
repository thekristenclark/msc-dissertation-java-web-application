package com.dissertation.WritingApp.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    // Constructor-based injection
 //   @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        super();
    	this.userRepository = userRepository;
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findUserByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

         if (user == null) {
        	 throw new UsernameNotFoundException("No user has been found. Please try again.");
     
         } 
         
         return new CustomUserDetails(user.getUsername(), user.getPassword(), authorities(), user.getFullname());
    }
    
    public Collection<? extends GrantedAuthority> authorities() {
    	  return Arrays.asList(new SimpleGrantedAuthority("USER"));
    	 }
   
}
