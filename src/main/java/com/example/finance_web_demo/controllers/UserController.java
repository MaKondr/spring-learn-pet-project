package com.example.finance_web_demo.controllers;


import com.example.finance_web_demo.models.Role;
import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.services.UserService;
import com.example.finance_web_demo.util.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;

    @Autowired
    public UserController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("role", new Role());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user/user";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@ModelAttribute("user") User user) {
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldErrors());
            return "user/create-user";
        }
        userService.createUser(user);
        return "redirect:/user";

    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model
            , @PathVariable long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "user/user-modified";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user
            , BindingResult bindingResult
            , @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            return "user/user-modified";
        }
        userService.updateUser(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions() {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(UserNotCreatedException ex) {
        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotUpdatedException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(UserNotUpdatedException ex) {


        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(SQLException ex) {
        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void hasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
    }
}
