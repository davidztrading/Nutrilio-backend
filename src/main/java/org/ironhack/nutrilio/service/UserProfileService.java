package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.dtos.UserProfileDTO;
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserRepository;
import org.ironhack.nutrilio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List; // Import necesario añadido

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    // CREATE / UPDATE
    @Transactional
    public void saveOrUpdateProfile(String email, UserProfileDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserProfile profile = (user.getProfile() != null) ? user.getProfile() : new UserProfile();

        profile.setName(dto.getName());
        profile.setAge(dto.getAge());
        profile.setWeight(dto.getWeight());
        profile.setHeight(dto.getHeight());
        profile.setActivityLevel(dto.getActivityLevel());
        profile.setNutritionalGoal(dto.getNutritionalGoal());
        profile.setUser(user);

        profileRepository.save(profile);
        user.setProfile(profile);
        userRepository.save(user);
    }

    // READ
    public UserProfile getProfileByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getProfile() == null) {
            throw new RuntimeException("El usuario no tiene un perfil creado");
        }
        return user.getProfile();
    }

    // DELETE
    @Transactional
    public void deleteProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getProfile() != null) {
            UserProfile profile = user.getProfile();
            user.setProfile(null);
            userRepository.save(user);
            profileRepository.delete(profile);
        }
    }

    // SUGGESTIONS
    public List<String> getMealSuggestions(String email) {
        UserProfile profile = getProfileByUser(email);

        if ("PERDER_PESO".equalsIgnoreCase(profile.getNutritionalGoal())) {
            return List.of("Ensalada de quinoa", "Salmón a la plancha");
        }
        return List.of("Pollo con arroz", "Pasta integral");
    }
}