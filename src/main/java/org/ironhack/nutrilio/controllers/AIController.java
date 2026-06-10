package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.service.NutritionalAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private NutritionalAIService nutritionalAIService;

    @GetMapping("/recommendations")
    public String getRecommendations() {
        return nutritionalAIService.generateDietPlan();
    }
}