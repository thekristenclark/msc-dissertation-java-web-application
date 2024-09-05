package com.dissertation.WritingApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.repositories.EditorRepository;

@Service
public interface EditorService {

    Editor saveEditor(Editor editor);
    
    // fetch the latest editor
    Editor findLatestEditorByUserId(ObjectId userId);
    
    // fetch all editors for a User
    List<Editor> findAllEditorsByUserId(ObjectId objectId);

	Editor findEditorByStoryId(String storyId);
	
	List<Editor> findEditorsByUserId(String userId);

}
