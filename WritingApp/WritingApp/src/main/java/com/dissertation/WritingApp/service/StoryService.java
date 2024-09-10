package com.dissertation.WritingApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.Story;

@Service
public interface StoryService {

    List<Story> findByUserId(String userId);

    Story createStory(Story story);

    Story updateStory(String id, Story story);

    void deleteStory(String id);

	Story save(Story story);
    
}
