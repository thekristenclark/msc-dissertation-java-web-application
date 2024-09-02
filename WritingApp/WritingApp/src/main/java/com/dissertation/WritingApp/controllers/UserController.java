package com.dissertation.WritingApp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.service.UserService;
import com.dissertation.WritingApp.dtos.UserDto;

@Controller
public class UserController {

//	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
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
	 public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
	  User user = userService.findByUsername(userDto.getUsername());
	  if (user != null) {
	   model.addAttribute("Userexist", user);
	   return "register";
	  }
	  userService.save(userDto);
	  return "redirect:/register?success";
	 }
	 
	  @GetMapping("/confirm")
	  public String confirmAccount(@RequestParam("token") String token) {
	      boolean confirmed = userService.confirmUser(token);

	      if (confirmed) {
	          return "Account successfully confirmed. You can now log in.";
	      } else {
	          return "Confirmation failed. Invalid token.";
	      }
	  }
	
/*	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String username) {
		User user = userRepository.findUserByUsername(username);
		if (user != null) {
			return user.getUsername();
		} else {
			return "User not found.";
		}
	} */
}