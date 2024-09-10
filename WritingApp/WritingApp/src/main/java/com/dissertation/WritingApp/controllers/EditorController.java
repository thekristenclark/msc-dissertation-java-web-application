package com.dissertation.WritingApp.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dissertation.WritingApp.domain.Editor;
import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.service.EditorService;
import com.dissertation.WritingApp.service.UserService;

/**
 * Controller responsible for operations related to the Editor feature within the application.
 * Allows users to create, view, and update stories through the editor form.
 * Key functionalities include:
 * - Displaying a new editor form for story creation.
 * - Retrieving and displaying an existing editor form based on a story ID.
 * - Submitting and saving editor data, including story content, character notes, and diagram data.
 * 
 * The controller interacts with the currently authenticated user, ensuring that each story is associated 
 * with a specific user account. It also manages default values and data structures for new editor instances 
 * and handles form submissions for story updates.
 */

@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private UserService userService;

    // Display the editor form for the currently logged-in user.
    @GetMapping("/editor-new")
    public String showCreateNewEditorForm(Model model) {
    	
        // Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        
        // Fetch the User object based on the username
        User user = userService.findByUsername(username);
        
        if (user != null) {
          	Editor editor = new Editor();
          		// Create a new editor instance with default values
            	editor.setUserId(user.getUserId());
            	editor.setStoryTitle("Enter story title");
            	editor.setCharNotes("Write your character notes here!");
            	editor.setStory("Write your story here!");
            	
            	// Set default diagram data for a new editor
            	String defaultDiagramData = "{ \"class\": \"GraphLinksModel\", " +
                        "\"nodeDataArray\": [" +
                        "{\"key\":-1,\"category\":\"Start\",\"loc\":\"-237 41\",\"text\":\"Start\"}," +
                        "{\"key\":-2,\"category\":\"End\",\"loc\":\"277 696\",\"text\":\"End\"}," +
                        "{\"category\":\"Conditional\",\"text\":\"Critical\\nchoice\",\"key\":-14,\"loc\":\"40 165\"}," +
                        "{\"text\":\"Stasis\",\"key\":-5,\"loc\":\"-100 230\"}," +
                        "{\"text\":\"Trigger\",\"key\":-6,\"loc\":\"180 230\"}," +
                        "{\"category\":\"Comment\",\"text\":\"GraphLinksModel\\nalso allows Groups\",\"key\":-7,\"loc\":\"362 230\"}," +
                        "{\"text\":\"The Quest\",\"key\":-8,\"loc\":\"-64 41\"}," +
                        "{\"text\":\"Surprise\",\"key\":-9,\"loc\":\"164 41\"}," +
                        "{\"text\":\"Critical Choice\",\"key\":-10,\"loc\":\"40 617\"}," +
                        "{\"text\":\"Reversal\",\"key\":-12,\"loc\":\"180 320\"}," +
                        "{\"text\":\"Resolution\",\"key\":-13,\"loc\":\"-100 320\"}," +
                        "{\"text\":\"Trigger\",\"key\":-15,\"loc\":\"277 617\"}," +
                        "{\"category\":\"Conditional\",\"text\":\"Critical Choice\",\"key\":-16,\"loc\":\"40 460\"}," +
                        "{\"text\":\"Climax\",\"key\":-18,\"loc\":\"-100 525\"}," +
                        "{\"text\":\"Resolution\",\"key\":-17,\"loc\":\"180 525\"}" +
                        "]," +
                        "\"linkDataArray\": [" +
                        "{\"from\":-1,\"to\":-8}," +
                        "{\"from\":-8,\"to\":-9}," +
                        "{\"from\":-5,\"to\":-13}," +
                        "{\"from\":-6,\"to\":-12}," +
                        "{\"from\":-15,\"to\":-2}," +
                        "{\"from\":-14,\"to\":-5,\"text\":\"choice one\"}," +
                        "{\"from\":-14,\"to\":-6,\"text\":\"choice 2\"}," +
                        "{\"from\":-9,\"to\":-14}," +
                        "{\"from\":-13,\"to\":-16}," +
                        "{\"from\":-12,\"to\":-16}," +
                        "{\"from\":-16,\"to\":-18,\"text\":\"Redemption\"}," +
                        "{\"from\":-16,\"to\":-17,\"text\":\"Fall\"}," +
                        "{\"from\":-18,\"to\":-10}," +
                        "{\"from\":-17,\"to\":-10}," +
                        "{\"from\":-10,\"to\":-15}" +
                        "]}";
            	editor.setDiagramData(defaultDiagramData);            	
            	
                // Generate a unique ID for the form
                String storyId = UUID.randomUUID().toString();
                editor.setStoryId(storyId);
                System.out.println("The generated Id is: " + storyId);
                model.addAttribute("editor", editor);
                
        } else {
            model.addAttribute("error", "User not found");
        }
        
        return "editor";
    }
    
    // Display the form to edit an existing editor by Story ID
    @GetMapping("/editor")
    public String showEditorForm(@RequestParam(value = "id", required = false) String storyId, Model model) {
        if (storyId != null && !storyId.isEmpty()) {	// fetch editor by story ID
            Editor editor = editorService.findEditorByStoryId(storyId);

            if (editor != null) {
                model.addAttribute("editor", editor);
                return "editor";
            } else {
                model.addAttribute("error", "Editor not found");
                return "error"; // or redirect to an error page
            }
        } else {
            model.addAttribute("error", "Invalid story ID");
            return "error"; // or redirect to an error page
        }
    }      

    // Handles submission of the editor form (creating and updating)
    @PostMapping("/editor")
    public String submitEditorForm(@ModelAttribute Editor editor, @RequestParam ("storyId") String storyId,
    		@RequestParam("diagramData") String diagramData,@RequestParam("charNotes") String charNotes,
    		@RequestParam("story") String story, @RequestParam("charMapData") String charMapData,
    		String storyTitle, Model model) {
        
    	// Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        System.out.println("Story Id: " + storyId);
        
        // Fetch the User object based on the username
        User user = userService.findByUsername(username);
        if (user != null) {
        	
            // Print out the current and updated values for debugging
            System.out.println("Current charMapData: " + editor.getCharMapData());
            System.out.println("Updated charMapData: " + charMapData);

            
            System.out.println("Submitted storyId: " + storyId);  // Check the UUID value

            // If editor exists, update its fields
            if (editor != null) {
            	editor.setStoryTitle(storyTitle);
            	editor.setDiagramData(diagramData);
            	editor.setCharMapData(charMapData);
            	editor.setCharNotes(charNotes);
            	editor.setStory(story);
            	editor.setCreateDate(LocalDateTime.now());
                editorService.saveEditor(editor);
                model.addAttribute("message", "Editor updated successfully!");
//                System.out.println("Editor updated and saved to database");	// for debugging
            } 
        } else {
            model.addAttribute("error", "User not found");
        }
        
        return "redirect:/home";
    }   
}