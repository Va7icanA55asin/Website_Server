package com.github.va7icana55asin.website_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping({"", "/"})
public class MainPageController {

    @Value("${app.github-url}")
    private String githubUrl;

    @Autowired
    public MainPageController() {
    }

    @GetMapping
    public String getMainPage(Model model, Authentication authentication){
        model.addAttribute("authenticated", (authentication != null && authentication.isAuthenticated()));
        LocalDateTime now = LocalDateTime.now();
        String date = now.getDayOfWeek().name() + " " + now.getMonth() + " " + now.getDayOfMonth() + ", " + now.getYear();
        model.addAttribute("time", date);
        model.addAttribute("githubUrl", githubUrl);
        return "mainPage";
    }
}
