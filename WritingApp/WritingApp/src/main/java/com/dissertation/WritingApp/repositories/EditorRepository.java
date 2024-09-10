package com.dissertation.WritingApp.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dissertation.WritingApp.domain.Editor;

@Repository
public interface EditorRepository extends MongoRepository<Editor, String> {
    
    // Fetch the latest editor	
   Editor findByUserIdOrderByCreateDateDesc(ObjectId userId);
   
   // Fetch all editors by user ID
   List<Editor> findByUserId(ObjectId objectId);

   Editor findByStoryId(String storyId);
   
   List<Editor> findByUserId(String userId);

}
