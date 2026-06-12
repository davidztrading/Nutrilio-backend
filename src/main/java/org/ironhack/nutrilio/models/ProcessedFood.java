package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@DiscriminatorValue("PROCESSED")

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedFood extends Food {


    private String brand;
    private String ingredientsList;
}