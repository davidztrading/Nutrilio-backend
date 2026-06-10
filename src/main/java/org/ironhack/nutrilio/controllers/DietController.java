package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    // --- GET (LECTURA) ---
    @GetMapping
    public ResponseEntity<List<Diet>> getAllDiets() {
        return ResponseEntity.ok(dietService.getAllDiets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diet> getDietById(@PathVariable Long id) {
        return dietService.getDietById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- POST (GENERACIÓN Y CREACIÓN) ---
    @PostMapping("/generate")
    public ResponseEntity<Diet> generateDiet(@RequestBody UserProfile profile) {
        Diet generatedDiet = dietService.generateDietForUser(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedDiet);
    }

    // --- PUT (ACTUALIZACIÓN) ---
    @PutMapping("/{id}")
    public ResponseEntity<Diet> updateDiet(@PathVariable Long id, @RequestBody Diet dietDetails) {
        return dietService.updateDiet(id, dietDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- DELETE (ELIMINACIÓN) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiet(@PathVariable Long id) {
        if (dietService.deleteDiet(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}