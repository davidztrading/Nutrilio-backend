package org.ironhack.nutrilio.models;



import jakarta.persistence.*;

import lombok.*;



@Entity

@Table(name = "foods")

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@ToString(exclude = "diet")

public class Food {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;



    private String name;

    private Double calories;



// AÑADE ESTO: Constructor que acepta String y double

    public Food(String name, Double calories) {

        this.name = name;

        this.calories = calories;

    }

}

