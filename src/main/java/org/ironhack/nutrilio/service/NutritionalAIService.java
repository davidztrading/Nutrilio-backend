package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NutritionalAIService {

    @Autowired
    private UserRepository userRepository;

    public String generateDietPlan() {
        // 1. Obtener email del usuario logueado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Buscar usuario en base de datos
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Obtener el perfil relacionado
        UserProfile profile = user.getProfile();

        if (profile == null) {
            return "Por favor, configura tu perfil en /api/profiles/update para recibir recomendaciones.";
        }

        // 4. Construir el prompt para la IA
        return String.format(
                "Generando plan nutricional para %s. Datos de usuario: " +
                        "Peso: %.1f kg, Edad: %d años, Nivel de actividad: %s, Objetivo: %s.",
                profile.getName(),
                profile.getWeight(),
                profile.getAge(),
                profile.getActivityLevel(),
                profile.getNutritionalGoal()
        );
    }
}