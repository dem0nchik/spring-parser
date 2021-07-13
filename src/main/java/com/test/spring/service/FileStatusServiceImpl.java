package com.test.spring.service;

import com.test.spring.dao.FileDao;
import com.test.spring.dao.FileStatusDao;
import com.test.spring.dao.UserDao;
import com.test.spring.models.FileModel;
import com.test.spring.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.UUID;

@Service
public class FileStatusServiceImpl implements FileStatusService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileStatusDao fileStatusDao;

    @Autowired
    private UserDao userDao;


    @Override
    public FileModel getOne(int id) {
        return null;
    }

    @Override
    public void add(FileModel fileModel) {

    }

    @Override
    public void addResult(MultipartFile multipartFile, Authentication authentication, HashMap<String, Integer> map) {
        FileModel fileModel = new FileModel();
        UUID uuid = UUID.randomUUID();
        String filePath = "resources/results/"+ uuid +".obj";

        fileModel.setName(multipartFile.getOriginalFilename());
        fileModel.setSize(multipartFile.getSize());
        fileModel.setFilePath(filePath);

        //save result in local file (obj)
        fileDao.saveResultInFile(map, filePath);

        //save file result in db
        fileDao.saveResultInDb(fileModel);

        //get ids file and user
        fileModel = fileDao.getByFilepath(filePath);
        UserModel user = (UserModel) authentication.getPrincipal();
        user = userDao.getUserByUsername(user.getUsername());

        //save information about user and file
        fileStatusDao.save(fileModel.getId(), user.getId());
    }
}
