package com.example.APIClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableMethodSecurity
public class Securisation {
   @Bean
   /*  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
           .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated());

    return http.build();
    }*/
   
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF pour les tests
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/hello").permitAll() // Autorise l'accès public à /hello
            .anyRequest().authenticated()          // Protège tout le reste
        )
        .formLogin(Customizer.withDefaults()); // Réactive le formulaire de login standard
    
    return http.build();
}

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
       return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }


}
