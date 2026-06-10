package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // Este método es el que fallaba porque no estaba declarado explícitamente
    // Spring ahora entenderá cómo buscar un perfil dado un usuario
    Optional<UserProfile> findByUser(User user);
}