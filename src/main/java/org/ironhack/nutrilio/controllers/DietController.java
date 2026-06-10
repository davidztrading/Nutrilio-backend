package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// BLOQUE DE CONFIGURACIÓN DEL CONTROLADOR REST
@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final DietService dietService;

    // El constructor se llama exactamente igual que la clase
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    // Endpoints básicos de consulta
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

    // BLOQUE DE ENDPOINT DE ENTRADA PARA LA IA
    // Este método recibirá el perfil del usuario y disparará la generación con OpenAI
    @PostMapping("/generate")
    public ResponseEntity<Diet> generateDiet(@RequestBody UserProfile profile) {
        Diet generatedDiet = dietService.generateDietForUser(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedDiet);
    }
}