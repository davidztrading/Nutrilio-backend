package org.ironhack.nutrilio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDTO {
    private Long id;
    private String name;
    private Double calories;
}