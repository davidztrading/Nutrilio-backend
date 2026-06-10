package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.dtos.UserProfileDTO;
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserProfileRepository;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveOrUpdateProfile(String email, UserProfileDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

        // Usamos el perfil existente o creamos uno nuevo
        UserProfile profile = (user.getProfile() != null) ? user.getProfile() : new UserProfile();

        profile.setUser(user);
        profile.setWeight(dto.getWeight());
        profile.setHeight(dto.getHeight());
        profile.setGoal(dto.getNutritionalGoal());
        profile.setGoal(dto.getActivityLevel());

        profileRepository.save(profile);

        // Vinculamos el perfil al usuario si es nuevo
        if (user.getProfile() == null) {
            user.setProfile(profile);
            userRepository.save(user);
        }
    }
}