package org.ironhack.nutrilio.models;

import jakarta.persistence.*;
import lombok.*;
import org.ironhack.nutrilio.enums.Role;

// BLOQUE DE CONFIGURACIÓN DE ENTIDAD (JPA Y LOMBOK)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BLOQUE DE CREDENCIALES DE ACCESO UNICAS
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // BLOQUE DE RELACIÓN ONE-TO-ONE (Configura la clave foránea y el borrado en cascada)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;
}