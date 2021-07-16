package com.test.spring.controllers;

import com.test.spring.dao.UserDao;
import com.test.spring.models.UserModel;
import com.test.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean err, Model model) {
        if (Boolean.TRUE.equals(err)) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/sign_up")
    public String getSignUp(@ModelAttribute("user") UserModel user) {
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute("user") @Valid UserModel user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/sign_up";
        }
        boolean isAdded = userService.add(user);
        if (!isAdded) {
            model.addAttribute("usernameError", true);
            return "/sign_up";
        }
        return "redirect:/";
    }
}
