// Implements StoryService, Handles CRUD operations with StoryRepository,
// and Manages time stamps for creation and updates.

package com.dissertation.WritingApp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.Story;
import com.dissertation.WritingApp.repositories.StoryRepository;
import com.dissertation.WritingApp.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService{

//    @Autowired
    private StoryRepository storyRepository;

    public Story save(Story story) {
    	return storyRepository.save(story);
    }
    
    @Override
    public List<Story> findByUserId(String userId) {
        return storyRepository.findByUserId(userId);
    }

    @Override
    public Story createStory(Story story) {
        story.setCreatedAt(LocalDateTime.now());
        story.setUpdatedAt(LocalDateTime.now());
        return storyRepository.save(story);
    }

    @Override
    public Story updateStory(String id, Story story) {
        if (storyRepository.existsById(id)) {
            story.setId(id);
            story.setUpdatedAt(LocalDateTime.now());
            return storyRepository.save(story);
        }
        return null; // or throw an exception if story not found
    }

    @Override
    public void deleteStory(String id) {
        storyRepository.deleteById(id);
    }
	
	
}
