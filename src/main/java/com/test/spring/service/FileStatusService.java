package com.test.spring.service;

import com.test.spring.models.FileModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


public interface FileStatusService {
    FileModel getOne(int id);
    void add(FileModel fileModel);

    void addResult(MultipartFile multipartFile, Authentication authentication, HashMap<String, Integer> map);
}
