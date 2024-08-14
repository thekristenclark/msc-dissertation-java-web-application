package com.dissertation.WritingApp.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.Role;
import com.dissertation.WritingApp.domain.UserRole;
import com.dissertation.WritingApp.repositories.UserRepository;


@Service
public class MongoAuthUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MongoAuthUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        com.dissertation.WritingApp.domain.User user = userRepository.findUserByUsername(userName);

/*        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); */
      
//        user.getAuthorities()
//          .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getName())));
//
//        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        
        Set<GrantedAuthority> grantedAuthorities = user.getAuthorities()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
        
    }

}