package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User; // <--- ¡Asegúrate de que coincida con tu paquete real!
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Al usar Lombok (@Data), setPassword y getPassword existen automáticamente
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}