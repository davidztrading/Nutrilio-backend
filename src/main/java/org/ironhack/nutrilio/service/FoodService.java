package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Food;
import org.ironhack.nutrilio.repository.FoodRepository;
import org.ironhack.nutrilio.dtos.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<FoodResponseDTO> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(food -> new FoodResponseDTO(food.getId(), food.getName(), food.getCalories()))
                .collect(Collectors.toList());
    }

    public FoodResponseDTO getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado con ID: " + id));
        return new FoodResponseDTO(food.getId(), food.getName(), food.getCalories());
    }
}