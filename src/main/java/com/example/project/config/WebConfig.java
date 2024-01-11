package com.example.project.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:src/main/resources/static/upload").addResourceLocations("file:///D:/temp/");
        //addResourceHandler 자바를 제외한 나머지 폴더(resources) 전부를 허용하겠다.
        //addResourceLocations 자바 내부경로. 어느 recourse? (src부터 경로를 설정해야함)
        //addResourceLocations 외부드라이브경로. 슬래쉬 3개
    }


}
