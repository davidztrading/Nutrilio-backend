package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.service.NutritionAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private NutritionAIService nutritionAIService;

    @GetMapping("/advice")
    public String getAdvice(@RequestParam String foodName, @RequestParam Integer calories) {
        return nutritionAIService.getNutritionalAdvice(foodName, calories);
    }
}