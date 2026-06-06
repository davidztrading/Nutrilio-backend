package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// BLOQUE DE CONFIGURACIÓN DEL CONTROLADOR REST
@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    // BLOQUE DE ENDPOINTS HTTP
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long id) {
        return userProfileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile profile) {
        UserProfile updatedProfile = userProfileService.saveOrUpdateProfile(profile);
        return ResponseEntity.ok(updatedProfile);
    }
}