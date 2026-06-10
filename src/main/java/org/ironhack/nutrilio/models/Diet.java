package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data // Esto genera automáticamente setName, setCreatedAt, etc.
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Campo necesario para setName
    private String description;
    private LocalDate createdAt; // Campo necesario para setCreatedAt

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DietItem> items;
}