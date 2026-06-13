package org.ironhack.nutrilio.service;



import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ironhack.nutrilio.dtos.DietDTO;

import org.ironhack.nutrilio.dtos.DietItemDTO;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;



@Service

public class NutritionalAIService {



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

                "Dame 5 platos para: " + goal + ". Devuélvelos estrictamente en formato JSON array con esta estructura: " +

                        "[{ \"nombre\": \"string\", \"calorias\": 0, \"proteinas\": 0, \"carbohidratos\": 0, \"grasas\": 0 }]")));



        ResponseEntity<Map> response = restTemplate.postForEntity(

                "https://api.openai.com/v1/chat/completions",

                new HttpEntity<>(body, headers),

                Map.class

        );



        Map<String, Object> bodyResponse = response.getBody();

        if (bodyResponse != null && bodyResponse.containsKey("choices")) {

            List<Map<String, Object>> choices = (List<Map<String, Object>>) bodyResponse.get("choices");

            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

            String jsonContent = (String) message.get("content");



            try {

// AQUÍ ESTÁ LA LÓGICA DE PROCESAMIENTO PROFESIONAL

                ObjectMapper mapper = new ObjectMapper();

                List<DietItemDTO> listaComidas = mapper.readValue(jsonContent, new TypeReference<List<DietItemDTO>>(){});



                DietDTO dto = new DietDTO();

                dto.setName("Plan nutricional IA: " + goal);

                dto.setFoods(listaComidas);



                return dto;

            } catch (Exception e) {

                throw new RuntimeException("Error al procesar el JSON de la IA: " + e.getMessage());

            }

        }



        throw new RuntimeException("Error al obtener respuesta de la IA");

    }

}

