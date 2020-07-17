package com.huseyinaydin.config;

import com.huseyinaydin.SpringBootSpringSecurityTrackLoggedInUsersApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");*/
        //registry.addResourceHandler("/img/**").addResourceLocations("file:" + SpringBootSpringSecurityTrackLoggedInUsersApplication.IMAGE_DIR);
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry
                .addResourceHandler("/uploads/**")
                .addResourceLocations("classpath:/static/uploads/");
    }
}
