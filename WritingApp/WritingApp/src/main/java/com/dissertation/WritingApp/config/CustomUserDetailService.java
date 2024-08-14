package com.dissertation.WritingApp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.repositories.UserRepository;

@Service
public class CustomUserDetailService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         com.dissertation.WritingApp.domain.User user = userRepository.findUserByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

   
}
