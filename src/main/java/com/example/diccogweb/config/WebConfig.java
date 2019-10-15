//package com.example.diccogweb.config;
//
//import io.swagger.models.HttpMethod;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class WebConfig implements WebMvcConfigurer {
//
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedOrigins("*")
//                        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
//                        .allowCredentials(false)
//                        .maxAge(360000);
//            }
//        };
//
//    }
//}
