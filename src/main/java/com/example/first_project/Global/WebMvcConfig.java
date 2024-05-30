package com.example.first_project.Global;

import com.example.first_project.FirstProjectApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 원하는 URL 패턴 설정
                .addResourceLocations(FirstProjectApplication.getOsType().getResourceHandler()); // 정적 자원이 위치한 디렉토리 설정
    }
}
