package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class Index {

    private final UserService userService;

    @Autowired
    public Index(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(@ModelAttribute("user") User user, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.findUserByUsername(auth.getName()));

        return "index";
    }
}
