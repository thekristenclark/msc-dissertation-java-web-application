package com.dissertation.WritingApp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dissertation.WritingApp.domain.Story;

public interface StoryRepository extends MongoRepository<Story, Long>{

	List<Story> findByUserId(String userId);
	
	boolean existsById(String id);	// checks if a Story with the given ID exists in the repository
	
	void deleteById(String id);
	
}
