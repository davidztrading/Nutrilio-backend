package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;

// BLOQUE DE IDENTIFICACIÓN DE SUBCLASE
@Entity
@DiscriminatorValue("PROCESSED")
// BLOQUE DE CONFIGURACIÓN DE LOMBOK PARA HERENCIA
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedFood extends Food {

    // BLOQUE DE ATRIBUTOS EXCLUSIVOS DE ALIMENTOS PROCESADOS
    private String brand;
    private String ingredientsList;
}