package com.test.spring.service;

import com.test.spring.models.FileResultModel;

import java.util.HashMap;
import java.util.List;

public interface FileResultsService {
    List<FileResultModel> getAllResults();
    HashMap<String, Integer> getResultsFromFile(String filepath);
}
