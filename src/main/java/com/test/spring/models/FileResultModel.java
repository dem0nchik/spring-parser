package com.test.spring.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResultModel {
    private Date dateAdding;
    private String username;
    private String filename;
    private long fileSize;
    private String filepath;
}
