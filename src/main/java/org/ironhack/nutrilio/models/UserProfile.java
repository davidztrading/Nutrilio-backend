package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;
import org.ironhack.nutrilio.enums.ActivityLevel;
import org.ironhack.nutrilio.enums.NutritionalGoal;

// BLOQUE DE CONFIGURACIÓN DE ENTIDAD (JPA Y LOMBOK)
@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BLOQUE DE ATRIBUTOS DATOS FÍSICOS
    private String name;
    private Double weight;       // In kg
    private Double height;       // In cm
    private Integer age;

    // BLOQUE DE ATRIBUTOS NUTRICIONALES (ENUMS)
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    private NutritionalGoal nutritionalGoal;

    // BLOQUE DE RELACIÓN BIDIRECCIONAL (Apunta al dueño de la relación)
    @OneToOne(mappedBy = "profile")
    private User user;
}