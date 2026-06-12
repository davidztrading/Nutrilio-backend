package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@DiscriminatorValue("FRESH")

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FreshFood extends Food {


    private String originCountry;
    private Boolean isOrganic;
}