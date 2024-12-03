package com.marcospedroso.facens.correlato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Correlato API")
                        .description("API para atender projeto Correlato")
                        .version("0.0.1")
                        .license(new License()
                                .name("Marcos Duarte")
                                .url("http://www.seusite.com.br")
                        )
                );
    }

}