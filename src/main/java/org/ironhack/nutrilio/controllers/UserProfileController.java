package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.dtos.UserProfileDTO;
import org.ironhack.nutrilio.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @PostMapping
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDTO dto, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("No autorizado");
        }

        String username = authentication.getName();
        profileService.saveOrUpdateProfile(username, dto);
        return ResponseEntity.ok("Datos guardados y vinculados correctamente al usuario: " + username);
    }
}