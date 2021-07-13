package com.test.spring.models;


import java.util.Date;

public class FileResultModel {
    private Date dateAdding;
    private String username;
    private String filename;
    private long fileSize;
    private String filepath;

    public FileResultModel(Date dateAdding, String username, String filename, long fileSize, String filepath) {
        this.dateAdding = dateAdding;
        this.username = username;
        this.filename = filename;
        this.fileSize = fileSize;
        this.filepath = filepath;
    }
    public FileResultModel(){}

    public Date getDateAdding() {
        return dateAdding;
    }

    public void setDateAdding(Date dateAdding) {
        this.dateAdding = dateAdding;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
