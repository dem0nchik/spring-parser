package com.test.spring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        if(authentication != null) {
            model.addAttribute("isAuth", true);
        }
        return "index";
    }


}
