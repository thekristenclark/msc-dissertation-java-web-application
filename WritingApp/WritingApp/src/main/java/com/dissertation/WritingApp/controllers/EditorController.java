package com.dissertation.WritingApp.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            // Find the latest Edkitor entry or create new
            Editor editor = editorService.findLatestEditorByUserId(user.getUserId());
            
            // if editor is null, initialize with default values
            if (editor == null) {
          	editor = new Editor();
            	editor.setUserId(user.getUserId());
            	editor.setStoryTitle("Enter story title");
            	// set default values for charNotes, story, and diagramData
            	editor.setCharNotes("Write your character notes here!");
            	editor.setStory("Write your story here!");
            	
            	// Set default diagram data if the editor is new
            	String defaultDiagramData = "{ \"class\": \"GraphLinksModel\", " +
                        "\"nodeDataArray\": [" +
                        "{\"key\":-1,\"category\":\"Start\",\"loc\":\"-237 41\",\"text\":\"Start\"}," +
                        "{\"key\":-2,\"category\":\"End\",\"loc\":\"277 696\",\"text\":\"End\"}," +
                        "{\"category\":\"Conditional\",\"text\":\"Is data\\ntree-like?\",\"key\":-14,\"loc\":\"40 165\"}," +
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
                        "{\"from\":-14,\"to\":-5,\"text\":\"Yes\"}," +
                        "{\"from\":-14,\"to\":-6,\"text\":\"No\"}," +
                        "{\"from\":-9,\"to\":-14}," +
                        "{\"from\":-13,\"to\":-16}," +
                        "{\"from\":-12,\"to\":-16}," +
                        "{\"from\":-16,\"to\":-18,\"text\":\"Yes\"}," +
                        "{\"from\":-16,\"to\":-17,\"text\":\"No\"}," +
                        "{\"from\":-18,\"to\":-10}," +
                        "{\"from\":-17,\"to\":-10}," +
                        "{\"from\":-10,\"to\":-15}" +
                        "]}";
            	editor.setDiagramData(defaultDiagramData);
//            	editor.setDiagramData("{ \"class\": \"GraphLinksModel\", \"nodeDataArray\": [...], \"linkDataArray\": [...] }"); // default diagram data
//            	
//            	model.addAttribute("editor", editor");
//            } else {
//            	
//           
            }             
            
            // add attributes to the model
            model.addAttribute("userId", user.getUserId().toString());
            model.addAttribute("ktitle", editor.getStoryTitle());
            model.addAttribute("editor", editor != null ? editor : new Editor());
//            model.addAttribute("charNotes", editor.getCharNotes());
//            model.addAttribute("story", editor.getStory());
//            model.addAttribute("diagramData", editor.getDiagramData());
                  //      model.addAttribute("diagramData", editor != null ? editor.getDiagramData() : "");
        } else {
            model.addAttribute("error", "User not found");
        }
        
        return "editor";
    }

    // submission of the editor form
    @PostMapping("/editor")
    public String submitEditorForm(@ModelAttribute Editor editor, @RequestParam("diagramData") String diagramData, @RequestParam("charNotes") String charNotes, @RequestParam("story") String story, String storyTitle, Model model) {
        
    	// Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Fetch the User object based on the username
        User user = userService.findByUsername(username);
        if (user != null) {
        	
            // Print out the current and updated values for debugging
            System.out.println("Current diagramData: " + editor.getDiagramData());
            System.out.println("Updated diagramData: " + diagramData);
            
            System.out.println("Current charNotes: " + editor.getCharNotes());
            System.out.println("Updated charNotes: " + charNotes);
            
            System.out.println("Current story: " + editor.getStory());
            System.out.println("Updated story: " + story);
            
            System.out.println("Curent Story Title: " + editor.getStoryTitle());
            System.out.println("Updated Story Title: " + storyTitle);

            // If editor exists, update its fields
            if (editor != null) {
            	editor.setStoryTitle(storyTitle);
                editor.setDiagramData(diagramData);
                editor.setCharNotes(charNotes);
                editor.setStory(story);
                editor.setCreateDate(LocalDateTime.now());
                editorService.saveEditor(editor);
                model.addAttribute("message", "Editor updated successfully!");
                System.out.println("Editor updated and saved to database");
            } else {
            	System.out.println();
                // Handle the case where there is no existing editor
                editor = new Editor();
                editor.setUserId(user.getUserId());
                editor.setStoryTitle(storyTitle);
                editor.setDiagramData(diagramData);
                editor.setCharNotes(charNotes);
                editor.setStory(story);
                editor.setCreateDate(LocalDateTime.now());
                editorService.saveEditor(editor);
                model.addAttribute("message", "Editor created and saved successfully!");
                System.out.println("New editor created and saved to database");
            }
        } else {
            model.addAttribute("error", "User not found");
        }
//        if (user != null) {
//            // Set creation date and userId, then save the editor
//            editor.setCreateDate(LocalDateTime.now());
//            editor.setUserId(user.getUserId());
//            editor.setDiagramData(diagramData);
//            editor.setCharNotes(charNotes);
//            editor.setStory(story);
//            editorService.saveEditor(editor);
//            model.addAttribute("message", "Editor updated successfully!");
//            System.out.println("Submitted to database");
//        } else {
//            model.addAttribute("error", "User not found");
//        }
        
        return "redirect:/home";
    }
}