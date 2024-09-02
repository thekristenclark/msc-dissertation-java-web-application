package com.dissertation.WritingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.repositories.UserRepository;
import com.dissertation.WritingApp.service.UserService;

//@Controller
//public class AuthController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//   
//
//    @GetMapping("/register")
//    public String register(Model model) {
//  //      model.addAttribute("user", new User(null, null, null));
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "redirect:/login";
//    }
//    
//  @GetMapping("/confirm")
//  public String confirmAccount(@RequestParam("token") String token) {
//      boolean confirmed = userService.confirmUser(token);
//
//      if (confirmed) {
//          return "Account successfully confirmed. You can now log in.";
//      } else {
//          return "Confirmation failed. Invalid token.";
//      }
//  }
//
//}

//@RestController
//@RequestMapping("/auth")
//public class AuthController {

//    @Autowired
//    private UserService userService;
//    
//  @Autowired
//  private UserRepository userRepository;
//  
//@Autowired
//private PasswordEncoder passwordEncoder;

//@GetMapping("/login")
//public String login() {
//    return "login";
//}
//
//    @PostMapping("/register")
//    public String register(@RequestBody User user) {
//        userService.registerUser(user);
//        return "Registration successful. Please check your email to confirm your account.";
//    }

//    @GetMapping("/confirm")
//    public String confirmAccount(@RequestParam("token") String token) {
//        boolean confirmed = userService.confirmUser(token);
//
//        if (confirmed) {
//            return "Account successfully confirmed. You can now log in.";
//        } else {
//            return "Confirmation failed. Invalid token.";
//        }
//    }
//}