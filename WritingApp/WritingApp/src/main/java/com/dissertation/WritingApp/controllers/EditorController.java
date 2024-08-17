package com.dissertation.WritingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.service.EditorService;



@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping("/editor")
    public String showCreateEditorForm(Model model) {
        model.addAttribute("editor", new Editor());
        return "editor";
    }

    @PostMapping("/editor")
    public String submitArticleForm(@ModelAttribute Editor editor, Model model) {
        editorService.saveEditor(editor);
        model.addAttribute("message", "Editor created successfully!");
        return "editor";
    }
}
