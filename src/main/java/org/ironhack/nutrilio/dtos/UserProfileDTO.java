package org.ironhack.nutrilio.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserProfileDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "La edad debe ser mayor que 0")
    private Integer age;

    @NotNull(message = "El peso es obligatorio")
    private Double weight;

    @NotNull(message = "La altura es obligatoria")
    private Double height;

    @NotBlank(message = "El nivel de actividad es obligatorio")
    private String activityLevel;

    @NotBlank(message = "El objetivo nutricional es obligatorio")
    private String nutritionalGoal;
}