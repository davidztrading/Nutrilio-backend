package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.dtos.DietDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class NutritionalAIService {

    // Asegúrate de que esto tome la clave de la variable de entorno, no del archivo
    @Value("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")
    private String apiKey;

    public DietDTO generateDirectly(String goal) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(Map.of("role", "user", "content",
                "Dame 3 platos para: " + goal + ". Respuesta solo nombres separados por coma")));

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.openai.com/v1/chat/completions",
                new HttpEntity<>(body, headers),
                Map.class
        );

        // Extracción segura de la respuesta
        Map<String, Object> bodyResponse = response.getBody();
        if (bodyResponse != null && bodyResponse.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) bodyResponse.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String sugerencias = (String) message.get("content");

            DietDTO dto = new DietDTO();
            dto.setName("Plan IA: " + goal);
            // Limpiamos los espacios en blanco después de la coma
            dto.setFoods(Arrays.stream(sugerencias.split(","))
                    .map(String::trim)
                    .toList());
            return dto;
        }

        throw new RuntimeException("Error al obtener respuesta de la IA");
    }
}