package org.ironhack.nutrilio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*; // Asegúrate de importar esto

@Entity
@Table(name = "user_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private Double weight;
    private Double height;
    private String activityLevel;
    private String nutritionalGoal;
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}