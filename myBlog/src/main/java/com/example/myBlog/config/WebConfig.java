package com.example.myBlog.config;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:5173") // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // Important for cookies and authorization headers
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map config= new HashMap();
        config.put("cloud_name","ddimszygz");
        config.put("api_key","915161956322736");
        config.put("api_secret","28A1obNbGV8pzf9HHbTTIE-WprM");
        return new Cloudinary(config);
    }
}
