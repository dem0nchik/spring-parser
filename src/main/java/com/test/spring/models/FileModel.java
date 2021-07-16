package com.test.spring.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FileModel {
    private int id;
    private String name;
    private long size;
    private String lastModified;
    private String filePath;
}
