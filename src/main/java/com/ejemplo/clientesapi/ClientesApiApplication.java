package com.ejemplo.clientesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EntityScan(basePackages = "com.ejemplo.clientesapi.model") // Especifica el paquete de las entidades
@PropertySource("classpath:application.properties")
public class ClientesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientesApiApplication.class, args);
    }
}
