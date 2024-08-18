package com.dissertation.WritingApp.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Display the editor form for the currently logged-in user.
    @GetMapping("/editor")
    public String showCreateEditorForm(Model model) {
    	
        // Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Fetch the User object based on the username
        User user = userService.findByUsername(username);
        if (user != null) {
            // Find the latest Editor entry or create new
            Editor editor = editorService.findLatestEditorByUserId(user.getUserId());
            model.addAttribute("userId", user.getUserId().toString());
            model.addAttribute("editor", editor != null ? editor : new Editor());
        } else {
            model.addAttribute("error", "User not found");
        }
        
        return "editor";
    }

    // submission of the editor form
    @PostMapping("/editor")
    public String submitEditorForm(@ModelAttribute Editor editor, Model model) {
        
    	// Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Fetch the User object based on the username
        User user = userService.findByUsername(username);
        if (user != null) {
            // Set creation date and userId, then save the editor
            editor.setCreateDate(LocalDateTime.now());
            editor.setUserId(user.getUserId());
            editorService.saveEditor(editor);
            model.addAttribute("message", "Editor updated successfully!");
        } else {
            model.addAttribute("error", "User not found");
        }
        
        return "redirect:/home";
    }
}