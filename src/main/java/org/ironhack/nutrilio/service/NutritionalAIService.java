package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.*;
import org.ironhack.nutrilio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NutritionalAIService {

    @Autowired private DietRepository dietRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private FoodRepository foodRepository;
    @Autowired private DietItemRepository dietItemRepository;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Transactional
    public Diet generateDietPlan(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Diet diet = new Diet();
        diet.setUser(user);
        diet.setName("Plan IA");
        diet.setCreatedAt(LocalDate.now());
        diet.setDescription("Dieta generada por IA");
        diet.setItems(new ArrayList<>());
        diet = dietRepository.save(diet);

        String[] nombres = {"Pollo", "Arroz", "Ensalada"};
        for (String nombre : nombres) {
            Food food = foodRepository.findByName(nombre.trim())
                    .orElseGet(() -> foodRepository.save(new Food(nombre.trim(), 0.0)));

            DietItem item = new DietItem();
            item.setFood(food);
            item.setDiet(diet);
            item.setQuantity(1);

            dietItemRepository.save(item);
            diet.getItems().add(item);
        }
        return diet;
    }
}