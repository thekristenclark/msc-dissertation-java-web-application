// handles http requests for new story creation from the home page

package com.dissertation.WritingApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dissertation.WritingApp.models.Story;
import com.dissertation.WritingApp.service.StoryService;

@Controller
public class StoryController {

	private StoryService storyService;
	
    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> getStoriesByUserId(@PathVariable String userId) {
        List<Story> stories = storyService.findByUserId(userId);
        return ResponseEntity.ok(stories);
    }

//    @PostMapping
//    public ResponseEntity<Story> createStory(@RequestBody Story story) {
//        Story createdStory = storyService.createStory(story);
//        return ResponseEntity.ok(createdStory);
//    }
    
    @PostMapping
    public String saveStory(@RequestParam("storyId") String storyId,
                            @RequestParam("userId") String userId,
                            @RequestParam("storyTitle") String storyTitle,
                            @RequestParam("storyContent") String storyContent) {
        Story story = new Story();
        story.setId(storyId);
        story.setUserId(userId);
        story.setTitle(storyTitle); // Add this line
        story.setContent(storyContent);
        storyService.save(story);
        return "redirect:/"; // redirect to home or list of stories
    }

    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable String id, @RequestBody Story story) {
        Story updatedStory = storyService.updateStory(id, story);
        return ResponseEntity.ok(updatedStory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }
	
}
