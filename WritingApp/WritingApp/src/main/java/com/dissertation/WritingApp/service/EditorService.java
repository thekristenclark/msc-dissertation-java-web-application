package com.dissertation.WritingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.repositories.EditorRepository;

@Service
public class EditorService {

    @Autowired
    private EditorRepository editorRepository;

    public Editor saveEditor(Editor editor) {
        return editorRepository.save(editor);
    }
}