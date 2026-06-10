package org.ironhack.nutrilio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DietItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    @JsonBackReference // Detiene la recursión hacia atrás
    private Diet diet;

    @Override
    public String toString() {
        return "DietItem{id=" + id + "}";
    }
}