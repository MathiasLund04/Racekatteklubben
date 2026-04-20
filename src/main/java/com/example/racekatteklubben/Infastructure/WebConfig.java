package com.example.racekatteklubben.Infastructure;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Bruges til at snakke med uploads der befinder sig udenfor /src (udefra) da SpringBoot ikke selv kender til de uploadede filer
    //Spring kender faktisk kun fra /src/main/resources/static og ikke /uploads
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:uploads/images/");
    }
}