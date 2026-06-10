package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.repository.DietRepository;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class NutritionalAIService {
    @Autowired private DietRepository dietRepository;
    @Autowired private UserRepository userRepository;

    @Transactional
    public Diet generateDietPlan(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Diet newDiet = new Diet();
        newDiet.setUser(user);
        newDiet.setName("Plan Nutricional IA");
        newDiet.setCreatedAt(LocalDate.now());
        newDiet.setDescription("Plan personalizado para objetivo: " + user.getProfile().getNutritionalGoal());

        return dietRepository.save(newDiet);
    }
}