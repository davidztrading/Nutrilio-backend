package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.dtos.UserRegistrationDTO; // Import correcto
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Usamos UserRegistrationDTO para el registro
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody UserRegistrationDTO userDTO) {
        return userService.registerUser(userDTO);
    }
}