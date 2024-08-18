package com.dissertation.WritingApp.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dissertation.WritingApp.models.Editor;

@Repository
public interface EditorRepository extends MongoRepository<Editor, String> {
    
   Editor findByUserIdOrderByCreateDateDesc(ObjectId userId);
	
}
