package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.dtos.DietDTO;
import org.ironhack.nutrilio.service.NutritionalAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diet")
public class NutritionalAIController {

    @Autowired
    private NutritionalAIService service;

    @PostMapping("/generate")
    public ResponseEntity<DietDTO> generate(@RequestParam String goal) {
        return ResponseEntity.ok(service.generateDirectly(goal));
    }
}