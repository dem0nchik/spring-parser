package com.test.spring;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileValidator implements Validator{
    @Override
    public ResultValidation validate(File file) {
        if(!file.exists()) {
            return new ResultValidation(false, "file is not exist");
        } else if(!file.isFile()) {
            return new ResultValidation(false, "is not file");
        } else if(file.length() == 0) {
            return new ResultValidation(false, "file is empty");
        }

        return new ResultValidation(true, "");
    }
}
