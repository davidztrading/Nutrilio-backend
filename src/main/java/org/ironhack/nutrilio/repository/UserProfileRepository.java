package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BLOQUE DE CONFIGURACIÓN DEL REPOSITORIO DE PERFILES
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}