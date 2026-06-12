package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.models.DietItem;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    public Optional<Diet> getDietById(Long id) {
        return dietRepository.findById(id);
    }

    @Transactional
    public Diet saveDiet(Diet diet) {
        // Asegura la relación bidireccional necesaria para persistir los DietItems
        if (diet.getItems() != null) {
            for (DietItem item : diet.getItems()) {
                item.setDiet(diet);
            }
        }
        return dietRepository.save(diet);
    }

    @Transactional
    public Optional<Diet> updateDiet(Long id, Diet dietDetails) {
        return dietRepository.findById(id).map(diet -> {
            diet.setName(dietDetails.getName());
            diet.setDescription(dietDetails.getDescription());
            // Si también actualizas items, procesarlos aquí igual que en saveDiet
            return dietRepository.save(diet);
        });
    }

    public boolean deleteDiet(Long id) {
        if (dietRepository.existsById(id)) {
            dietRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- LÓGICA DE NEGOCIO (IA / GENERACIÓN) ---

    @Transactional
    public Diet generateDietForUser(UserProfile profile) {
        Diet newDiet = new Diet();
        newDiet.setName("Plan para: " + profile.getNutritionalGoal());
        newDiet.setDescription("Dieta recomendada basada en peso: " + profile.getWeight() +
                "kg y nivel de actividad: " + profile.getActivityLevel());
        newDiet.setCreatedAt(LocalDate.now());

        return dietRepository.save(newDiet);
    }
}