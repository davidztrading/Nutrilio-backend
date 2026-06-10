package org.ironhack.nutrilio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitar CSRF (estándar para APIs REST)
                .csrf(csrf -> csrf.disable())

                // 2. Definir políticas de acceso
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll() // RUTA PÚBLICA
                        .anyRequest().authenticated() // RESTO PROTEGIDO
                )

                // 3. Configurar API Stateless (sin sesiones HTTP)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. Usar autenticación básica (o JWT en el futuro)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}