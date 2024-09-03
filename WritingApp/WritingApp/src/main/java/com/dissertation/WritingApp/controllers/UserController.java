// handles http requests related to user registration and email confirmation

package com.dissertation.WritingApp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.service.InvalidTokenException;
import com.dissertation.WritingApp.service.UserService;
import com.dissertation.WritingApp.dtos.UserDto;

@Controller
public class UserController {

//	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private UserDetailsService userDetailsService;
	private UserService userService;

	@Autowired
	public UserController(UserService userService, UserDetailsService userDetailsService) {
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}
	
	 @GetMapping("/home")
	 public String home(Model model, Principal principal) {
	  UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
	  model.addAttribute("userdetail", userDetails);
	  return "home";
	 }
	
	 @GetMapping("/login")
	 public String login(Model model, UserDto userDto) {
		 model.addAttribute("user", userDto);
		 return "login";
	 }

	 @GetMapping("/register")
	 public String register(Model model, UserDto userDto) {
	  model.addAttribute("user", userDto);
	  return "register";
	 }

	 @PostMapping("/register")
	 public String registerUser(@ModelAttribute("user") UserDto userDto, Model model) {
	  User existingUser = userService.findByUsername(userDto.getUsername());
	  if (existingUser != null) {
	   model.addAttribute("UserExist", "User already exists.");
	   return "register";
	  }
//	    if (userDto.isEmailVerified() == null) {
//	        userDto.setEmailVerified(false); // Set default value if null
//	    }
	  userService.registerUser(userDto);
	  return "redirect:/register?success";
	 }

	 
	 @GetMapping("/email-confirmation")
	 public String confirmEmail(@RequestParam("token") String token, Model model) {
	     boolean isVerified = userService.verifyEmail(token); // Implement verifyEmail in your service
	     
	     if (isVerified) {
	         model.addAttribute("message", "Your email has been successfully verified.");
	     } else {
	         model.addAttribute("message", "The verification link is invalid or has expired.");
	     }
	     
	     return "email-confirmation"; // Return the name of the view template
	 }
}