package com.udacity.jwdnd.course1.superduperdrive.controller;

import com.udacity.jwdnd.course1.superduperdrive.model.Note;
import com.udacity.jwdnd.course1.superduperdrive.services.NoteService;
import com.udacity.jwdnd.course1.superduperdrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;


    @PostMapping("/notes")
    public String addNote(@ModelAttribute("Note") Note note, Authentication authentication, Model model) {

        String username = authentication.getName();
        Long userId = userService.getUser(username).getUserId();
        note.setUserId(userId);

        if (note.getNoteId() == null) {
            this.noteService.addNote(note);
            model.addAttribute("notes", this.noteService.getNotesByUserId(userId));
            model.addAttribute("successMessage", "Note successfully added!");
        } else {
            this.noteService.updateNote(note);
            model.addAttribute("successMessage", "Note successfully updated!");
        }
        return "result";
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable("noteId") Long noteId, Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("successMessage", "Note successfully deleted!");
        return "result";
    }
}
