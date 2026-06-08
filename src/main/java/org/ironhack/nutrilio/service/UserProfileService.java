package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.models.UserProfile;
import org.ironhack.nutrilio.repository.UserRepository;
import org.ironhack.nutrilio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<UserProfile> getProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile saveOrUpdateProfile(UserProfile profile) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserProfile existingProfile = user.getProfile();

        if (existingProfile != null) {
            existingProfile.setName(profile.getName());
            existingProfile.setWeight(profile.getWeight());
            existingProfile.setHeight(profile.getHeight());
            existingProfile.setAge(profile.getAge());
            existingProfile.setActivityLevel(profile.getActivityLevel());
            existingProfile.setNutritionalGoal(profile.getNutritionalGoal());
            return userProfileRepository.save(existingProfile);
        } else {
            profile.setUser(user);
            return userProfileRepository.save(profile);
        }
    }
}