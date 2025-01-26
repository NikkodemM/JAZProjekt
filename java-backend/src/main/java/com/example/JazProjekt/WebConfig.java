package com.example.JazProjekt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Dopuszczamy wszystkie endpointy
                        .allowedOrigins("http://localhost:3000") // Dopuszczamy frontend React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Lista dozwolonych metod
                        .allowedHeaders("*") // Pozwalamy na wszystkie nagłówki
                        .allowCredentials(true); // Jeśli korzystasz z ciasteczek
            }
        };
    }
}
