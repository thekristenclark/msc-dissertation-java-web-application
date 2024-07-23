package com.dissertation.WritingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping
	public String showHomePage(Model model) {
		model.addAttribute("title", "Welcome to this website");
		model.addAttribute("about", "This is a simple web application using springboot.");
		return "home";
	}
	
}
