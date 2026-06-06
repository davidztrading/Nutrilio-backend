package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

// BLOQUE DE CONFIGURACIÓN DE ENTIDAD (JPA Y LOMBOK)
@Entity
@Table(name = "diets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BLOQUE DE ATRIBUTOS PROPIOS DE LA DIETA
    private String name;
    private Integer totalCalories;
    private LocalDate createdAt;

    // BLOQUE DE RELACIÓN MANY-TO-ONE (Varias dietas pueden pertenecer al mismo usuario)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // BLOQUE DE RELACIÓN MANY-TO-MANY (Crea una tabla intermedia 'diet_foods' en MySQL)
    @ManyToMany
    @JoinTable(
            name = "diet_foods",
            joinColumns = @JoinColumn(name = "diet_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;
}