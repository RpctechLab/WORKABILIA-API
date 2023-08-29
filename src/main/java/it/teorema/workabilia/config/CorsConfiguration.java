package it.teorema.workabilia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST")
                .allowedOrigins("*")
                .exposedHeaders("Origin", "Access-Control-Allow-Origin", "Content-Type",
        				"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
        				"Access-Control-Request-Headers", "App-Key");
    }
}