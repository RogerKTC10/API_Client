package com.example.APIClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;

@Configuration
@EnableMethodSecurity
public class Securisation {
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register").permitAll()
                .anyRequest().authenticated() )
        .formLogin(org.springframework.security.config.Customizer.withDefaults())
        .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
   }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
       return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
         return builder.baseUrl("https://api.externe.com").build();
    }

}
