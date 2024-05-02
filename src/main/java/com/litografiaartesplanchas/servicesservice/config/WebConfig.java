package com.litografiaartesplanchas.servicesservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The `WebConfig` class in Java configures CORS mappings to allow all origins, methods, and headers.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
     * This Java function configures CORS (Cross-Origin Resource Sharing) settings to allow all origins,
     * methods, and headers for all endpoints.
     * 
     * @param registry The `registry` parameter in the `addCorsMappings` method is an instance of the
     * `CorsRegistry` class. It is used to configure Cross-Origin Resource Sharing (CORS) for the
     * application. In this method, we are allowing all origins (`*`), all methods, and all headers
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
    }
}