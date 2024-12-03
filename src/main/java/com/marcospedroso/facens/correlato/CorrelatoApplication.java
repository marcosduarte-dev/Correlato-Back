package com.marcospedroso.facens.correlato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@PropertySource({"classpath:application.properties", "classpath:db.properties"})
public class CorrelatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorrelatoApplication.class, args);
	}

}
