package com.ensa.tp8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                // Configuration des règles d'autorisation
                .authorizeHttpRequests(auth -> auth
                        // Autoriser l'accès à Swagger UI et OpenAPI docs
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Produits/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Produits/**").hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.PUT, "/Produits/**").hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.DELETE, "/Produits/**").hasAuthority("SCOPE_write")
                        .anyRequest().authenticated())

                // Active l’authentification par JWT (OAuth2 Resource Server)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
