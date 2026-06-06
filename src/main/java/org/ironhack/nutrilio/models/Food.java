package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;
import org.ironhack.nutrilio.enums.FoodCategory;

// BLOQUE DE CONFIGURACIÓN DE HERENCIA EN BASE DE DATOS
@Entity
@Table(name = "foods")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "food_type", discriminatorType = DiscriminatorType.STRING)
// BLOQUE DE CONFIGURACIÓN DE LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BLOQUE DE INFORMACIÓN NUTRICIONAL BÁSICA (Por cada 100g)
    private String name;
    private Double calories;
    private Double proteins;
    private Double carbohydrates;
    private Double fats;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private FoodCategory category;
}