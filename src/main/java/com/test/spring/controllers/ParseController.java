package com.test.spring.controllers;

import com.test.spring.models.FileResultModel;
import com.test.spring.parser.Parser;
import com.test.spring.service.FileResultsService;
import com.test.spring.service.FileStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@Controller
public class ParseController {
    @Autowired
    private Parser parser;

    @Autowired
    private FileStatusService fileStatusService;

    @Autowired
    private FileResultsService fileResultsService;

    @GetMapping("/parse")
    public String parse() {
        return "parse";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,
                             Model model, Authentication authentication) throws IOException {
        if(multipartFile.getSize() > 1000000) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "File is too large. It must be less than 1mb.");
            return "result";
        } else if(multipartFile.getSize() == 0) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "File is empty");
            return "result";
        }

        HashMap<String, Integer> map = parser.countUniqueWords(multipartFile.getInputStream());

        fileStatusService.addResult(multipartFile, authentication, map);

        String data = parser.mapOfWordsToString(map);
        model.addAttribute("data" , data);
        model.addAttribute("count", (long) map.keySet().size());
        return "result";
    }

    @GetMapping("/results")
    public String results(Model model) {
        List<FileResultModel> results = fileResultsService.getAllResults();

        model.addAttribute("results", results);
        return "results";
    }

    @GetMapping("/view_result")
    public String view_result(@RequestParam("path") String filepath, Model model) {

        HashMap<String, Integer> map = fileResultsService.getResultsFromFile(filepath);

        String data = parser.mapOfWordsToString(map);
        model.addAttribute("data" , data);
        model.addAttribute("count", (long) map.keySet().size());
        return "result";
    }
}
