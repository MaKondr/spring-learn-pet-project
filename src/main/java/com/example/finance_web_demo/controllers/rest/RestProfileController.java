package com.example.finance_web_demo.controllers.rest;

import com.example.finance_web_demo.dto.ProfileDTO;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profile")
public class RestProfileController {
    private final ModelMapper modelMapper;
    private final UserProfileService userProfileService;

    @Autowired
    public RestProfileController(ModelMapper modelMapper, UserProfileService userProfileService) {
        this.modelMapper = modelMapper;
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfileById(@RequestParam(name = "id") Long id) {
        UserProfile byId = userProfileService.findById(id);
        return ResponseEntity.ok(modelMapper.map(byId, ProfileDTO.class));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createAccount(@RequestParam(name="id") Long id) {
        UserProfile profile = userProfileService.findById(id);
        userProfileService.createAccount(profile);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestParam(name="id") Long id) {
        userProfileService.deleteAccount(id);
    }


}
