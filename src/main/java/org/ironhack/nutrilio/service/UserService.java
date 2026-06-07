package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.repository.UserRepository;
import org.ironhack.nutrilio.dtos.UserRequestDTO;
import org.ironhack.nutrilio.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crear usuario desde DTO
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        User user = new User();
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword()); // En la fase de seguridad, aquí irá el PasswordEncoder

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getEmail());
    }

    // Listar todos los usuarios convertidos a DTO
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());
    }
}