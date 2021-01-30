package com.huade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    private final String images = "/Users/yaoyuan/Desktop/online_exam/file/image/";
    private final String mode = "/Users/yaoyuan/Desktop/Exam/file/mode/";
    private final String file = "/Users/yaoyuan/Desktop/online_exam/file/";

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+images);
        registry.addResourceHandler("/modes/**").addResourceLocations("file:"+mode);
        registry.addResourceHandler("/files/**").addResourceLocations("file:"+file);
    }
}
