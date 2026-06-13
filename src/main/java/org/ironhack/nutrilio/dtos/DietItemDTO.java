package org.ironhack.nutrilio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietItemDTO {

    private String nombre;
    private Integer calorias;
    private Integer proteinas;
    private Integer carbohidratos;
    private Integer grasas;

}