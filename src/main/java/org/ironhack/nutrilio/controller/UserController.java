package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.dtos.UserRequestDTO;
import org.ironhack.nutrilio.dtos.UserResponseDTO;
import org.ironhack.nutrilio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@RequestBody UserRequestDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }
}