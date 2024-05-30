package com.twd.SpringSecurityJWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // 허용할 Origin 주소
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP Method
                        .allowCredentials(true); // 클라이언트에서 쿠키 요청을 허용한다.
            }
        };
    }
}