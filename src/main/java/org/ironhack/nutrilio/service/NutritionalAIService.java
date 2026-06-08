package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NutritionalAIService {

    private final UserRepository userRepository;

    public NutritionalAIService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateDietPlan() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserProfile profile = user.getProfile();

        System.out.println("DEBUG: Buscando perfil para: " + email);
        System.out.println("DEBUG: Perfil encontrado: " + (profile != null));

        if (profile == null) {
            return "Error: No se encontró un perfil vinculado al usuario.";
        }

        return String.format("Plan nutricional para %s. Peso: %.1fkg, Edad: %d, Objetivo: %s.",
                profile.getName(), profile.getWeight(), profile.getAge(), profile.getNutritionalGoal());
    }
}