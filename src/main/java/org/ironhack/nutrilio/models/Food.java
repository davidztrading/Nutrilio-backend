package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foods")
@Getter @Setter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double calories;

    @Column(name = "food_type", nullable = false)
    private String foodType = "GENERAL"; // Valor por defecto aquí

    public Food(String name) {
        this.name = name;
        this.calories = 0.0;
        this.foodType = "GENERAL";
    }
}