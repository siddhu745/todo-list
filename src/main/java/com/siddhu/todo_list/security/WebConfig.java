package com.siddhu.todo_list.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origins}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all paths
                .allowedOrigins(allowedOrigin) // Allow the frontend to access the backend
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allow necessary HTTP methods
                .allowedHeaders("*")  // Allow any headers
                .allowCredentials(true);
    }
}
