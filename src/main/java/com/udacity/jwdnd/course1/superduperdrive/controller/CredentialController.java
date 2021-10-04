package com.udacity.jwdnd.course1.superduperdrive.controller;

import com.udacity.jwdnd.course1.superduperdrive.model.Credential;
import com.udacity.jwdnd.course1.superduperdrive.model.User;
import com.udacity.jwdnd.course1.superduperdrive.services.CredentialService;
import com.udacity.jwdnd.course1.superduperdrive.services.EncryptionService;
import com.udacity.jwdnd.course1.superduperdrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CredentialController {

    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;


    @PostMapping("/credentials")
    public String addCredential(@ModelAttribute("Credential") Credential credential, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getName());
        credential.setUserId(user.getUserId());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), user.getSalt()));

        if (credential.getCredentialId() == null) {
            this.credentialService.createCredential(credential);
            model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
            model.addAttribute("successMessage", "Credential successfully added!");
        } else {
            this.credentialService.updateCredential(credential);
            model.addAttribute("successMessage", "Credential successfully updated!");
        }
        return "result";
    }

    @GetMapping("/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Long credentialId, Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("successMessage", "Credential successfully deleted!");
        return "result";
    }
}
