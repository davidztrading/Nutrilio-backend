package org.ironhack.nutrilio.service;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// BLOQUE DE CONFIGURACIÓN DEL SERVICIO
@Service
public class UserService {

    private final UserRepository userRepository;

    // Inyección de dependencias por constructor (Buenas prácticas)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // BLOQUE DE LÓGICA DE NEGOCIO (Operaciones CRUD)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(User user) {
        // Aquí más adelante añadiremos el bloque de encriptación de contraseña con BCrypt
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}