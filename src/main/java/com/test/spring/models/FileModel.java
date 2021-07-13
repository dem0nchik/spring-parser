package com.test.spring.models;

public class FileModel {
    private int id;
    private String name;
    private long size;
    private String lastModified;
    private String filePath;

    public FileModel(int id, String name, long size, String lastModified, String filePath) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.lastModified = lastModified;
        this.filePath = filePath;
    }

    public FileModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
