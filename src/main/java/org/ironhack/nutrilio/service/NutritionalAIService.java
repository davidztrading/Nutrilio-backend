package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.dtos.DietDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class NutritionalAIService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public DietDTO generateDirectly(String goal) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(Map.of("role", "user", "content", "Dame 3 platos para: " + goal + ". Respuesta solo nombres separados por coma")));

        ResponseEntity<Map> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions", new HttpEntity<>(body, headers), Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        String sugerencias = (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");

        DietDTO dto = new DietDTO();
        dto.setId(0L);
        dto.setName("Plan IA: " + goal);
        dto.setFoods(Arrays.asList(sugerencias.split(",")));
        return dto;
    }
}