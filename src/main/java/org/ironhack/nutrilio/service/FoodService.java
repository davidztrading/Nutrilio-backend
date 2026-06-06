package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Food;
import org.ironhack.nutrilio.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// BLOQUE DE CONFIGURACIÓN DEL SERVICIO
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // BLOQUE DE LÓGICA DE NEGOCIO (Gestión del catálogo de alimentos)
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}