package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
      return new OpenAPI()
                .info(new Info()
                        .title("Cardio Surgery Illustrator API")
                        .version("1.0.0")
                        .description("API for managing modules and subjects in the Cardio Surgery Illustrator project"));
    }
}