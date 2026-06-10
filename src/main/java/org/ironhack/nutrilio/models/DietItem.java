package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diet_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DietItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "diet_id", nullable = false)
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;
}