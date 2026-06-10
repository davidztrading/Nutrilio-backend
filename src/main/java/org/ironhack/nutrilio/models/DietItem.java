package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DietItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private Integer quantity;
}