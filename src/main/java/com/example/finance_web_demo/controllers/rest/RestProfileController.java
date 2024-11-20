package com.example.finance_web_demo.controllers.rest;

import com.example.finance_web_demo.dto.ProfileDTO;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
        UserProfile byId = userProfileService.findById(id);
        return ResponseEntity.ok(modelMapper.map(byId, ProfileDTO.class));
    }

}
