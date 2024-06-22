package com.example.finance_web_demo.util.user;

import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findUserByUsernameCheck(user.getUsername()).isPresent())
            errors.rejectValue("username", null, "Такой пользователь существует");
        if(userService.findUserByEmailCheck(user.getEmail()).isPresent())
            errors.rejectValue("email", null, "Email уже занят");

    }
}
