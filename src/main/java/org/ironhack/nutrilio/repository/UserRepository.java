package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BLOQUE DE CONFIGURACIÓN DEL REPOSITORIO DE USUARIOS
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}