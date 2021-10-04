package com.udacity.jwdnd.course1.superduperdrive.controller;

import com.udacity.jwdnd.course1.superduperdrive.model.File;
import com.udacity.jwdnd.course1.superduperdrive.services.FileService;
import com.udacity.jwdnd.course1.superduperdrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class FileController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    //resource -> https://knowledge.udacity.com/questions/382441
    @PostMapping("/fileUpload")
    public String addFile(@RequestParam("fileUpload") MultipartFile fileUpload, File file, Authentication authentication, Model model) {
        Long userId = userService.getUser(authentication.getName()).getUserId();
        if (fileUpload.isEmpty()) {
            model.addAttribute("errorMessage", "You haven't chosen any file!");
        } else {
            String fileName = fileUpload.getOriginalFilename();
            if (!fileService.fileExist(fileName)) {
                try {
                    file.setUserId(userId);
                    file.setFileName(fileName);
                    file.setFileData(fileUpload.getBytes());
                    file.setContentType(fileUpload.getContentType());
                    file.setFileSize(fileUpload.getSize());

                    this.fileService.insertFile(file);
                    List<File> files = this.fileService.filesUpload(file.getUserId());
                    model.addAttribute("files", files);
                    model.addAttribute("successMessage", "File Succesfully Uploaded!");
                } catch (Exception ex) {
                    model.addAttribute("errorMessage", "File couldn't upload!");
                }
            } else {
                model.addAttribute("errorMessage", "File already exist!");
            }
        }
        return "result";
    }

    @GetMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable("fileId") Long fileId, Model model) {
        this.fileService.deleteFile(fileId);
        model.addAttribute("successMessage", "Successfully deleted!");
        return "result";
    }

    //resource -> https://knowledge.udacity.com/questions/288143
    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam String fileName) {
        File file = fileService.getFileByName(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file);

    }
}
