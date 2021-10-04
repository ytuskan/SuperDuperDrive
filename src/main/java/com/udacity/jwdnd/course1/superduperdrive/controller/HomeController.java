package com.udacity.jwdnd.course1.superduperdrive.controller;

import com.udacity.jwdnd.course1.superduperdrive.model.Credential;
import com.udacity.jwdnd.course1.superduperdrive.model.File;
import com.udacity.jwdnd.course1.superduperdrive.model.Note;
import com.udacity.jwdnd.course1.superduperdrive.model.User;
import com.udacity.jwdnd.course1.superduperdrive.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;

    @GetMapping("/home")
    public String getHomePage(File file, Note note, Credential credential, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getName());
        List<Credential> credentials = credentialService.getCredentials(user.getUserId());
        List<Note> notes = noteService.getNotesByUserId(user.getUserId());
        List<File> files = fileService.filesUpload(user.getUserId());
        model.addAttribute("key", user.getSalt());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        return "home";
    }
}