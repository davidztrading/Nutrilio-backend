package org.ironhack.nutrilio.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class NutritionAIService {

    private final ChatClient chatClient;

    public NutritionAIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String getNutritionalAdvice(String foodName, Integer calories) {
        String prompt = String.format(
                "Eres un experto en nutrición. El usuario quiere información sobre el alimento: %s, que tiene %d calorías. " +
                        "Dame un consejo nutricional breve y saludable sobre este alimento.",
                foodName, calories
        );

        return chatClient.prompt(prompt).call().content();
    }
}