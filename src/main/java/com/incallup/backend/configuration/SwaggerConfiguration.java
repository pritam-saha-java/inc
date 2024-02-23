package com.incallup.backend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI employeeManagementOpenAPI() {

        var api = new OpenAPI();
        api.info(new Info().title("Incallup")
                .license(new License().name("incallup"))
        );
        api.components(new Components());

        return api;
    }
}
