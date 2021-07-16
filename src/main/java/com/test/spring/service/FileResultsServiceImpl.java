package com.test.spring.service;

import com.test.spring.dao.FileDao;
import com.test.spring.dao.FileResultDao;
import com.test.spring.models.FileResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FileResultsServiceImpl implements FileResultsService {
    private final FileResultDao fileResultDao;
    private final FileDao fileDao;

    @Autowired
    public FileResultsServiceImpl(FileResultDao fileResultDao, FileDao fileDao) {
        this.fileResultDao = fileResultDao;
        this.fileDao = fileDao;
    }

    @Override
    public List<FileResultModel> getAllResults() {
        return fileResultDao.getAllResults();
    }

    @Override
    public HashMap<String, Integer> getResultsFromFile(String filepath) {
        return fileDao.getResultFromFile(filepath);
    }
}
