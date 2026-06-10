package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.*;
import org.ironhack.nutrilio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NutritionalAIService {

    @Autowired private DietRepository dietRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private FoodRepository foodRepository;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Transactional
    public Diet generateDietPlan(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        String sugerenciasIA = "Pollo, Arroz, Ensalada"; // Simulación para probar

        Diet diet = new Diet();
        diet.setUser(user);
        diet.setName("Plan IA");
        diet.setCreatedAt(LocalDate.now());
        diet.setDescription("Dieta generada por IA");
        diet.setItems(new ArrayList<>());

        for (String nombre : sugerenciasIA.split(",")) {
            String n = nombre.trim();

            Food food = foodRepository.findByName(n).orElseGet(() -> {
                Food newFood = new Food(n);
                newFood.setFoodType("GENERAL"); // Forzamos el valor aquí
                return foodRepository.save(newFood);
            });

            DietItem item = new DietItem();
            item.setFood(food);
            item.setDiet(diet);
            diet.getItems().add(item);
        }
        return dietRepository.save(diet);
    }

    private String obtenerSugerenciasDesdeIA(UserProfile profile) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String prompt = "Dame 3 platos para un objetivo de " + profile.getNutritionalGoal() +
                ". Responde SOLO los nombres separados por comas, sin nada más.";

        String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            // Por simplicidad, retornamos un ejemplo fijo.
            // Si funciona, cambia esto por el parseo de response.getBody()
            return "Pollo al horno, Ensalada de quinoa, Salmón a la plancha";
        } catch (Exception e) {
            return "Arroz, Pollo, Verduras";
        }
    }
}