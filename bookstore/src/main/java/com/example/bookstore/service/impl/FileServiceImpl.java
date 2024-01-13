package com.example.bookstore.service.impl;

import com.example.bookstore.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String UPLOAD_DIR;

    @Value("${file.path.comment}")
    private String UPLOAD_DIR_COMMENT;

    @Override
    public String uploadFile(MultipartFile file, boolean flag) {

        if(!flag){
            UPLOAD_DIR = UPLOAD_DIR_COMMENT;
        }

        if (file == null || file.isEmpty()) {
            return null;
        }

        // Generate a unique file name to avoid overwriting existing files
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Create the directory for storing uploaded files if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Copy the uploaded file to the upload directory
        Path targetPath = Paths.get(UPLOAD_DIR, fileName);
        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {

//        if(!flag){
//            UPLOAD_DIR = UPLOAD_DIR_COMMENT;
//        }

        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Resource resource = null;

        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                //check comment
                filePath = Paths.get(UPLOAD_DIR_COMMENT, fileName);
                resource = new UrlResource(filePath.toUri());
                if (!resource.exists() || !resource.isReadable()) {
//                new NotFoundException(Collections.singletonMap("fileName", fileName));
                }
            }
        } catch (MalformedURLException e) {
//            new ResourceNotFoundExceptionV2(Collections.singletonMap("fileName", fileName));
        }

        return resource;
    }

    @Override
    public void deleteFile(String fileName, boolean flag) {

        if(!flag){
            UPLOAD_DIR = UPLOAD_DIR_COMMENT;
        }
        try {
            if (StringUtils.isNotBlank(fileName)) {
                Files.deleteIfExists(Paths.get(UPLOAD_DIR, fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
