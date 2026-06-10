package org.ironhack.nutrilio.dtos;

import lombok.Data;
import java.util.List;

@Data
public class DietDTO {
    private Long id;
    private String name;
    private List<String> foodNames;
}