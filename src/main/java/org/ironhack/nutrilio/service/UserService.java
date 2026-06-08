package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.dtos.UserRegistrationDTO;
import org.ironhack.nutrilio.dtos.UserResponseDTO;
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar usuario usando DTO de registro
    public User registerUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        // Siempre encriptamos la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    // Listar todos los usuarios y convertirlos a DTO de respuesta
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());
    }

    // Buscar usuario por email (útil para el login o seguridad)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}