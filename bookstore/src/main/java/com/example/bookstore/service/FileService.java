package com.example.bookstore.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, boolean flag);

    Resource downloadFile(String filename);

    void deleteFile(String fileName, boolean flag);


}
