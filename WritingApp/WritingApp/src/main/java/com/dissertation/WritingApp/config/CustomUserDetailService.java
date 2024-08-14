package com.dissertation.WritingApp.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         com.dissertation.WritingApp.domain.User user = userRepository.findUserByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

         if (user == null) {
        	 throw new UsernameNotFoundException("No user has been found. Please try again.");
     
         }
         
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build();
         
         return new org.springframework.security.core.userdetails.User
        		 (user.getUsername(), user.getPassword(), new ArrayList<>());

               
    }

   
}
