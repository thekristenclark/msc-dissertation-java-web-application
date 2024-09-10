package com.dissertation.WritingApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.Editor;

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
