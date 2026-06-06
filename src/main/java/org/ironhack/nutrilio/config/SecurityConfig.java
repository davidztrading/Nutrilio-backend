package org.ironhack.nutrilio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// BLOQUE DE CONFIGURACIÓN DE SPRING SECURITY
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // BLOQUE DE ENCRIPTACIÓN: Registra el triturador de contraseñas de la Semana 11
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // BLOQUE DE REGLAS DE ACCESO: Define qué rutas son públicas y cuáles privadas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Configuración de los endpoints
                .authorizeHttpRequests(auth -> auth
                        // Permitimos que cualquiera pueda registrarse o ver usuarios (público)
                        .requestMatchers("/api/users/**").permitAll()
                        // Cualquier otra petición a la API requerirá autenticación
                        .anyRequest().authenticated()
                )
                // Desactivamos el formulario de Login por defecto de Spring
                .formLogin(form -> form.disable())
                // Activamos la autenticación básica para las pruebas iniciales
                .httpBasic(basic -> basic.init(http));

        return http.build();
    }
}