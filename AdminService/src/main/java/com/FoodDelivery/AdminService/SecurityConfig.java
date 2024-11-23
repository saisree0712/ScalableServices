package com.FoodDelivery.AdminService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for development/testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login,/users/registration")
                        .permitAll()
                        // Allow login,registration endpoint
                        .anyRequest().authenticated() // Secure other endpoints
                );
        return http.build();
    }

}