package com.dissertation.WritingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dissertation.WritingApp.models.Editor;

//import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/editor")

public class EditorController {

	@GetMapping
	public String showHomePage(Model model) {
	    model.addAttribute("editor", new Editor());
		return "editor.html";
	}
	
	@PostMapping("/editor")
	public String formSubmit(@ModelAttribute Editor editor, Model model) {
		model.addAttribute("editor", editor);
	    return "in post Method";
	//	return "editor.html";
	  }
	
}
