package com.dissertation.WritingApp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dissertation.WritingApp.domain.Editor;
import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.service.EditorService;
import com.dissertation.WritingApp.service.UserService;

/**
 * Controller responsible for handling story-related requests.
 * Specifically, it manages HTTP requests for displaying the home page
 * and showing the list of stories (editors) associated with the current user.
 */

@Controller
public class StoryController {

	@Autowired
    private EditorService editorService; // to handle editor-related operations

    @Autowired
    private UserService userService; // to handle user-related operations
    
    private UserDetailsService userDetailsService; // to load user details from usesrname
    
	@Autowired	// Constructor initializes the StoryController with userService and userDetailsService.
	public StoryController(UserService userService, UserDetailsService userDetailsService) {
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}

    /**
     * Handles GET requests for the home page.
     * Retrieves the current user's details and their associated stories (editors),
     * then adds them to the model for rendering in the home page view.
     * This method provides the code to render the home page with all of the stories
     * that have been previously saved in the database, associated with the currently logged-in user.
     */
    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
    	
  	  	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
  	  	model.addAttribute("userdetail", userDetails);
    	
        // Retrieves the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (user != null) {
            // Fetches all stories (editors) associated with the user
            List<Editor> editors = editorService.findAllEditorsByUserId(user.getUserId());
            model.addAttribute("editors", editors);
        } else {
            model.addAttribute("error", "User not found");
        }

        return "home";
    }
	
}
