package org.ironhack.nutrilio.dtos;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String email;
    private String password;
}