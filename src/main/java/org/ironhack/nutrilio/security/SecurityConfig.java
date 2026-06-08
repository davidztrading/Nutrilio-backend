package org.ironhack.nutrilio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para poder probar con Postman fácilmente
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll() // Registro público
                        .anyRequest().authenticated() // Todo lo demás requiere login
                )
                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            response.setStatus(200); // En lugar de redirigir, devolvemos OK
                            response.getWriter().write("Login exitoso");
                        })
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}