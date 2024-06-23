package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.services.UserProfileService;
import com.example.finance_web_demo.util.profile.ProfileNotFoundException;
import com.example.finance_web_demo.util.user.UserErrorResponse;
import com.example.finance_web_demo.util.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class MyProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public MyProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }



    @GetMapping("/{id}")
    public String profile(@PathVariable long id, Model model) {
        model.addAttribute("profile", userProfileService.findById(id));
        return "profile/profile";
    }

    @GetMapping("update/{id}")
    public String updateProfile(@PathVariable long id, Model model) {
        model.addAttribute("profile", userProfileService.findById(id));
        return "profile/profile-modified";
    }

    @PatchMapping("/{id}")
    public String updateProfile(@PathVariable long id,
                                @ModelAttribute("profile") @Valid UserProfile userProfile,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "profile/profile-modified";
        }
        userProfileService.update(id, userProfile);
        System.out.println(userProfileService.findById(id).getAccount());
        return "redirect:/profile/"+id;
    }

    @GetMapping()
    public String listProfile(Model model) {
        model.addAttribute("profiles", userProfileService.findAll());
        return "profile/profiles";
    }

    @GetMapping("/add-account")
    public String addAccount(@RequestParam(name = "id") long id) {

        userProfileService.createAccount(userProfileService.findById(id));
        return "redirect:/profile/" + id;
    }


    @ExceptionHandler(ProfileNotFoundException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions() {
        UserErrorResponse response = new UserErrorResponse(
                "Profile with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
