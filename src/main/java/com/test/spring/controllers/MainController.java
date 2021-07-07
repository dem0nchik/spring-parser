package com.test.spring.controllers;

import com.test.spring.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class MainController {
    @Autowired
    private Parser parser;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile multipartFile, Model model) throws IOException {
        HashMap<String, Integer> map = parser.countUniqueWords(multipartFile.getInputStream());
        String data = parser.stringMapOfWords(map);

        model.addAttribute("data" , data);
        model.addAttribute("count", (long) map.keySet().size());
        return "result";
    }
}
