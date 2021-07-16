package com.test.spring.service;

import com.test.spring.dao.FileDao;
import com.test.spring.dao.FileStatusDao;
import com.test.spring.dao.UserDao;
import com.test.spring.models.FileModel;
import com.test.spring.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
@Transactional
public class FileStatusServiceImpl implements FileStatusService {
    private final FileDao fileDao;
    private final FileStatusDao fileStatusDao;
    private final UserDao userDao;

    @Autowired
    public FileStatusServiceImpl(FileDao fileDao, FileStatusDao fileStatusDao, UserDao userDao) {
        this.fileDao = fileDao;
        this.fileStatusDao = fileStatusDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void addResult(MultipartFile multipartFile, Authentication authentication, HashMap<String, Integer> map) throws Exception {
        FileModel fileModel = new FileModel();
        UUID uuid = UUID.randomUUID();
        String filePath = "resources/results/" + uuid + ".obj";

        fileModel.setName(multipartFile.getOriginalFilename());
        fileModel.setSize(multipartFile.getSize());
        fileModel.setFilePath(filePath);

        try {
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
        } catch (Exception e) {
            if (new File(filePath).delete())
                System.out.println("file was deleted " + filePath);
            e.printStackTrace();
            throw new Exception("Data is not saved in file and DB");
        }
    }
}
