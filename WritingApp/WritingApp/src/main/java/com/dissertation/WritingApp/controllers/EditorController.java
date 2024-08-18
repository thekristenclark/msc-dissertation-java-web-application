package com.dissertation.WritingApp.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.service.EditorService;
import com.dissertation.WritingApp.service.UserService;



@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/editor")
    public String showCreateEditorForm(Model model) {
        
    	// specifies the current user account logged in
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	
    	// specifies the user id based on the current account
    	User user = userService.findByUsername(username);
    	if (user !=null) {
            model.addAttribute(user.getUserId().toString());
    	} else {
            model.addAttribute("userId", "Unknown");  // Handle the case where user is not found
        }
        
        model.addAttribute("editor", new Editor());
        return "editor";
    }

    @PostMapping("/editor")
    public String submitEditorForm(@ModelAttribute Editor editor, Model model) {	
    	
        // Set the userId on the editor before saving
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        
        // Automatically set createDate
        editor.setCreateDate(LocalDateTime.now());
        
        Editor savedEditor = editorService.saveEditor(editor);

        
        if (savedEditor != null) {
        	model.addAttribute("message", "Editor created successfully!");
            return "redirect:/home";
        } else {
            model.addAttribute("message", "Failed to create editor.");
            return "editor";
        }
    }
}
