package ru.lukin.spring.nic_zadacha.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173") // Разрешенный источник
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные методы
                .allowedHeaders("*") // Разрешенные заголовки
                .allowCredentials(true); // Разрешить передачу учетных данных (например, куки)
    }
}