package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.models.Diet; // IMPORTANTE: Asegúrate de tener este import
import org.ironhack.nutrilio.service.NutritionalAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class NutritionalAIController {

    @Autowired
    private NutritionalAIService nutritionalAIService;

    // Cambiamos ResponseEntity<String> por ResponseEntity<Diet>
    @PostMapping("/generate")
    public ResponseEntity<Diet> generateDiet(Authentication authentication) {
        String email = authentication.getName();

        // Ahora el servicio devuelve un objeto Diet, y el controlador lo recibe perfectamente
        Diet savedDiet = nutritionalAIService.generateDietPlan(email);

        return ResponseEntity.ok(savedDiet);
    }
}