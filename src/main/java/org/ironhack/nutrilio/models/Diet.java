package org.ironhack.nutrilio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
    @JsonManagedReference // Gestiona la serialización hacia adelante
    private List<DietItem> items = new ArrayList<>();

    @Override
    public String toString() {
        return "Diet{id=" + id + ", name='" + name + "'}";
    }
}