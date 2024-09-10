// handles http requests related to user registration and email confirmation

package com.dissertation.WritingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService, UserDetailsService userDetailsService) {
		this.userService = userService;
	}
	
	@GetMapping("/writeSimply")
	public String landing(Model model) {
		return "writeSimply";
	}
	
	 @GetMapping("/login")
	 public String login(Model model, User user) {
		 model.addAttribute("user", user);
		 return "login";
	 }

	 @GetMapping("/register")
	 public String register(Model model, User user) {
	  model.addAttribute("user", user);
	  return "register";
	 }

	 @PostMapping("/register")
	 public String registerUser(@ModelAttribute("user") User user, Model model) {
	  User existingUser = userService.findByUsername(user.getUsername());
	  if (existingUser != null) {
	   model.addAttribute("UserExist", "User already exists.");
	   return "register";
	  }
	  userService.registerUser(user);
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