package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// BLOQUE DE CONFIGURACIÓN DEL CONTROLADOR REST
@RestController
@RequestMapping("/api/diets")
public class DietController { // Mantenemos el estándar de nombres de tu proyecto

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    // BLOQUE DE ENDPOINTS HTTP
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

    @PostMapping
    public ResponseEntity<Diet> createDiet(@RequestBody Diet diet) {
        Diet createdDiet = dietService.saveDiet(diet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiet);
    }
}