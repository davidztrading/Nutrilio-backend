package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long id) {
        return userProfileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile profile) {
        return ResponseEntity.ok(userProfileService.saveOrUpdateProfile(profile));
    }
}