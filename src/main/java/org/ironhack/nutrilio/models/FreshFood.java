package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;

// BLOQUE DE IDENTIFICACIÓN DE SUBCLASE
@Entity
@DiscriminatorValue("FRESH")
// BLOQUE DE CONFIGURACIÓN DE LOMBOK PARA HERENCIA
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FreshFood extends Food {

    // BLOQUE DE ATRIBUTOS EXCLUSIVOS DE ALIMENTOS FRESCOS
    private String originCountry;
    private Boolean isOrganic;
}