package com.webstudy.webstudy.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "WebStudy",
                version = "v0.0.1",
                description = "api 정리"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {


}