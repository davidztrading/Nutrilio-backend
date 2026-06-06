package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.Diet;
import org.ironhack.nutrilio.repository.DietRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// BLOQUE DE CONFIGURACIÓN DEL SERVICIO
@Service
public class DietService {

    private final DietRepository dietRepository;

    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    // BLOQUE DE LÓGICA DE NEGOCIO (Historial de dietas)
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    public Optional<Diet> getDietById(Long id) {
        return dietRepository.findById(id);
    }

    public Diet saveDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    public void deleteDiet(Long id) {
        dietRepository.deleteById(id);
    }

    // BLOQUE FUTURO: Aquí inyectaremos el ChatClient de Spring AI para generar la dieta con OpenAI
}