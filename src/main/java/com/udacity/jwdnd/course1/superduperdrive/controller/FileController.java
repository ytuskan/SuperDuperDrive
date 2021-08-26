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

import java.util.List;

@Controller
@RequestMapping
public class FileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, File file, Authentication authentication, Model model) {

        String fileUploadSuccess = null;
        String fileUploadError = null;

        Long userId = userService.getUser(authentication.getName()).getUserId();
        String fileName = fileUpload.getOriginalFilename();
        if (fileUpload.isEmpty()){
            model.addAttribute("errorMessage", "You haven't chosen any file!");
        }else{
            if (!fileService.fileExist(fileName, userId)) {
                try {
                    file.setUserId(userId);
                    file.setFileName(fileName);
                    file.setFileData(fileUpload.getBytes());
                    file.setContentType(fileUpload.getContentType());
                    file.setFileSize(fileUpload.getSize());

                    this.fileService.insertFile(file);
                    List<File> files = this.fileService.filesUpload(file.getUserId());
                    model.addAttribute("files", files);
                    model.addAttribute("successMessage", "File Succesfully Upload!");
                } catch (Exception ex) {
                    model.addAttribute("errorMessage", "File couldn't uploaded!");
                }
            }else{
                model.addAttribute("errorMessage", "File is already exist!");
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

    @GetMapping("/download")
    public ResponseEntity download(@RequestParam String fileName, Authentication authentication) {

        Long userId = userService.getUser(authentication.getName()).getUserId();
        List<File> files = fileService.filesUpload(userId);
        File newFile = new File();

        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileName().equals(fileName)) {
                newFile = files.get(i);
            }
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + newFile.getFileName() + "\"")
                .body(newFile);
    }
}
