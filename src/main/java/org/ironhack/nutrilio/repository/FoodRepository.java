package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BLOQUE DE CONFIGURACIÓN DEL REPOSITORIO DE ALIMENTOS
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}