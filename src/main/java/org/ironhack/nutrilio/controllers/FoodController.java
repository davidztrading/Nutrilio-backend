package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.dtos.FoodResponseDTO;
import org.ironhack.nutrilio.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<FoodResponseDTO> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public FoodResponseDTO getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }
}