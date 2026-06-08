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

    public String getRecommendations() {
        // 1. Obtener email del contexto de seguridad
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Buscar usuario
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Obtener perfil
        UserProfile profile = user.getProfile();

        if (profile == null) {
            throw new RuntimeException("El usuario no tiene un perfil configurado");
        }

        // 4. Lógica de IA (aquí usarás los datos del profile)
        return String.format(
                "Generando plan para %s: Meta: %s, Actividad: %s, Peso: %.1fkg",
                profile.getName(),
                profile.getNutritionalGoal(),
                profile.getActivityLevel(),
                profile.getWeight()
        );
    }
}