package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

// BLOQUE DE CONFIGURACIÓN DEL SERVICIO
@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    // BLOQUE DE LÓGICA DE NEGOCIO
    public Optional<UserProfile> getProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile saveOrUpdateProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }
}