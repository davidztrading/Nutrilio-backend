package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BLOQUE DE CONFIGURACIÓN DEL REPOSITORIO DE DIETAS
@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
}