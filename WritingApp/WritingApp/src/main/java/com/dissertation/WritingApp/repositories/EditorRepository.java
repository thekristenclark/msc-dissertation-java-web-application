package com.dissertation.WritingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dissertation.WritingApp.models.Editor;

@Repository
public interface EditorRepository extends MongoRepository<Editor, String> {

	
	
}
