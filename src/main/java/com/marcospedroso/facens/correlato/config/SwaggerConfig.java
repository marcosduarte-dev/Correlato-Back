package com.marcospedroso.facens.correlato.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
	    server.setUrl("http://localhost:8080/correlato");
	    server.setDescription("Development");

	    Contact contatoMarcos = new Contact();
	    contatoMarcos.setName("Marcos Duarte");
	    contatoMarcos.setEmail("pe.marcos30@gmail.com");

	    Info information = new Info()
	    		.title("Correlato API")
	            .version("1.0")
	            .description("API desenvolvida para o projeto Correlato.")
	            .contact(contatoMarcos);
	       return new OpenAPI().info(information).servers(List.of(server));
	   }

}