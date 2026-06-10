package org.ironhack.nutrilio.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DietDTO {
    private Long id;
    private String name;
    private List<String> foods;
}