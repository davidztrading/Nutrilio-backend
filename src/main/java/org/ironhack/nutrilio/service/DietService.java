package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.DietRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// BLOQUE DE CONFIGURACIÓN DEL SERVICIO
@Service
public class DietService {

    private final DietRepository dietRepository;
    private final ChatClient chatClient;

    // Constructor que unifica la pasarela de datos y el motor de OpenAI
    public DietService(DietRepository dietRepository, ChatClient.Builder chatClientBuilder) {
        this.dietRepository = dietRepository;
        this.chatClient = chatClientBuilder.build();
    }

    // BLOQUE DE MÉTODOS DE CONSULTA BÁSICOS (Los que solucionan tus errores)
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    public Optional<Diet> getDietById(Long id) {
        return dietRepository.findById(id);
    }

    // BLOQUE DE GENERACIÓN DE DIETA CON INTELIGENCIA ARTIFICIAL
    public Diet generateDietForUser(UserProfile profile) {
        String prompt = String.format(
                "Genera un plan nutricional diario para un usuario con el nombre %s. " +
                        "Tiene %d años, pesa %.1f kg, mide %.1f cm. " +
                        "Su nivel de actividad es %s y su objetivo nutricional es %s. " +
                        "Devuelve un menú estructurado con desayuno, comida y cena.",
                profile.getName(), profile.getAge(), profile.getWeight(),
                profile.getHeight(), profile.getActivityLevel(), profile.getNutritionalGoal()
        );

        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        Diet diet = new Diet();
        diet.setName("Plan IA para " + profile.getName());
        diet.setCreatedAt(LocalDate.now());
        diet.setUser(profile.getUser());

        return dietRepository.save(diet);
    }
}