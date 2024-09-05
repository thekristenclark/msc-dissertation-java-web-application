//handles story creation
// handles http requests for new story creation from the home page

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

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.service.EditorService;
import com.dissertation.WritingApp.service.UserService;

@Controller
public class StoryController {

	@Autowired
    private EditorService editorService;

    @Autowired
    private UserService userService;
    
    private UserDetailsService userDetailsService;
    
	@Autowired
	public StoryController(UserService userService, UserDetailsService userDetailsService) {
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
    	
  	  	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
  	  	model.addAttribute("userdetail", userDetails);
    	
        // Retrieve the current user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (user != null) {
            // Fetch all stories (editors) associated with the user
            List<Editor> editors = editorService.findAllEditorsByUserId(user.getUserId());
            model.addAttribute("editors", editors);
        } else {
            model.addAttribute("error", "User not found");
        }

        return "home";
    }
	
//	private StoryService storyService;
//	
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<Story>> getStoriesByUserId(@PathVariable String userId) {
//        List<Story> stories = storyService.findByUserId(userId);
//        return ResponseEntity.ok(stories);
//    }
//
////    @PostMapping
////    public ResponseEntity<Story> createStory(@RequestBody Story story) {
////        Story createdStory = storyService.createStory(story);
////        return ResponseEntity.ok(createdStory);
////    }
//    
//    @PostMapping
//    public String saveStory(@RequestParam("storyId") String storyId,
//                            @RequestParam("userId") String userId,
//                            @RequestParam("storyTitle") String storyTitle,
//                            @RequestParam("storyContent") String storyContent) {
//        Story story = new Story();
//        story.setId(storyId);
//        story.setUserId(userId);
//        story.setTitle(storyTitle); // Add this line
//        story.setContent(storyContent);
//        storyService.save(story);
//        return "redirect:/"; // redirect to home or list of stories
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Story> updateStory(@PathVariable String id, @RequestBody Story story) {
//        Story updatedStory = storyService.updateStory(id, story);
//        return ResponseEntity.ok(updatedStory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
//        storyService.deleteStory(id);
//        return ResponseEntity.noContent().build();
//    }
	
}
