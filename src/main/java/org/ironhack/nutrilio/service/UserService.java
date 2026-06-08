package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.dtos.UserRegistrationDTO;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        // Encriptamos siempre antes de guardar
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}