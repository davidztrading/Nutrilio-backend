package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data // Esto genera automáticamente los Getters, Setters, etc.
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;
    private Double height;
    private String goal;

    // Añade estos campos si los estás llamando en el servicio
    private String name;
    private String activityLevel;
    private Integer age;
    private String nutritionalGoal;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}