package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.dtos.UserProfileDTO;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List; // Import necesario añadido

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    // 1. CREATE (POST)
    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody UserProfileDTO dto, Authentication auth) {
        userProfileService.saveOrUpdateProfile(auth.getName(), dto);
        return ResponseEntity.status(201).body("Perfil creado correctamente");
    }

    // 2. READ (GET)
    @GetMapping
    public ResponseEntity<UserProfile> getProfile(Authentication auth) {
        return ResponseEntity.ok(userProfileService.getProfileByUser(auth.getName()));
    }

    // 3. UPDATE (PUT)
    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDTO dto, Authentication auth) {
        userProfileService.saveOrUpdateProfile(auth.getName(), dto);
        return ResponseEntity.ok("Perfil actualizado correctamente");
    }

    // 4. DELETE (DELETE)
    @DeleteMapping
    public ResponseEntity<String> deleteProfile(Authentication auth) {
        userProfileService.deleteProfile(auth.getName());
        return ResponseEntity.ok("Perfil eliminado correctamente");
    }

    // 5. SUGGESTIONS (GET)
    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(Authentication auth) {
        return ResponseEntity.ok(userProfileService.getMealSuggestions(auth.getName()));
    }
}