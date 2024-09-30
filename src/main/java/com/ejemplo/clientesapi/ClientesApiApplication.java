package com.ejemplo.clientesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class ClientesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientesApiApplication.class, args);
    }
}
