package com.dissertation.WritingApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.domain.Editor;
import com.dissertation.WritingApp.repositories.EditorRepository;
import com.dissertation.WritingApp.service.EditorService;

@Service
//public abstract class EditorServiceImpl implements EditorService1 {
public class EditorServiceImpl implements EditorService {

    @Autowired
    private EditorRepository editorRepository;

    public Editor saveEditor(Editor editor) {
        return editorRepository.save(editor);
    }
    
    // fetch the latest editor
    public Editor findLatestEditorByUserId(ObjectId userId) {
        return editorRepository.findByUserIdOrderByCreateDateDesc(userId);
    }
    
    // fetch all editors for a User
    public List<Editor> findAllEditorsByUserId(ObjectId objectId) {
        return editorRepository.findByUserId(objectId);
    }

	public Editor findEditorByStoryId(String storyId) {
        return editorRepository.findByStoryId(storyId);
	}
	
    @Override
    public List<Editor> findEditorsByUserId(String userId) {
        return editorRepository.findByUserId(userId);
    }
	
}