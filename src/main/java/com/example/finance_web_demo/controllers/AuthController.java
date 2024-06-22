package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.services.UserService;
import com.example.finance_web_demo.util.user.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final UserService userService;
    @Autowired
    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
            return "/auth/registration";
        }
        userService.createUser(user);

        return "redirect:/auth/login";
    }

}
