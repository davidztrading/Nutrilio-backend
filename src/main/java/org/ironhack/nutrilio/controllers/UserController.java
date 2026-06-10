package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.dtos.UserRegistrationDTO;
import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDTO dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }
}