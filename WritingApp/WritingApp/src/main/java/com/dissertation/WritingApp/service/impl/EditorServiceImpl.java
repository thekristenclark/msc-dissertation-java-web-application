package com.dissertation.WritingApp.service.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.repositories.EditorRepository;

@Service
//public abstract class EditorServiceImpl implements EditorService1 {
public abstract class EditorServiceImpl {

//
//
//    private final EditorRepository editorRepository;
//
//    @Autowired
//    public EditorServiceImpl(EditorRepository editorRepository) {
//        this.editorRepository = editorRepository;
//    }
//
////    @Override
////    public Optional<Editor> findByUserId(ObjectId userId) {
////        return editorRepository.findByUserId(userId);
////    }
//    
//    @Override
//    public Editor saveEditor(Editor editor) {
//        return editorRepository.save(editor);
//    }
////    
////    @Override
////    public Editor findLatestEditorByUserId(ObjectId userId) {
////        return editorRepository.findByUserIdOrderByDate(userId);
////    }
}