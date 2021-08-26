package com.udacity.jwdnd.course1.superduperdrive.services;

import com.udacity.jwdnd.course1.superduperdrive.mapper.FileMapper;
import com.udacity.jwdnd.course1.superduperdrive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public File getFileUpload(Long fileId) {
        return fileMapper.findFile(fileId);
    }

    public void insertFile(File file) {
        fileMapper.insertFile(file);
    }

    public void deleteFile(Long fileId) {
        fileMapper.deleteFile(fileId);
    }

    public List<File> filesUpload(Long userId) {
        return fileMapper.findFilesByUserId(userId);
    }

    public File getFileByName(String fileName) {
        return fileMapper.findFileByName(fileName);
    }

    public Boolean fileExist(String fileName, Long userId) {
        Boolean exists = false;
        List<File> files = filesUpload(userId);
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileName().equals(fileName)) {
                exists = true;
            }
        }
        return exists;
    }

}
