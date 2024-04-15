package com.coderhouse.ecommerce.config;

import java.util.List; // Importa la clase List desde java.util

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@ComponentScan(basePackages = {"com.coderhouse.ecommerce.controller"})
public class SwaggerOptions {

    @Bean
    OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Full | Java | CoderHouse")
                        .version("1.0.1")
                        .description("La API REST proporciona endpoints para administrar un ecommerce con clientes, productos y facturas"
                                + ". Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) segun corresponda para sus entidades."
                                + "Los endpoints permiten listar, agregar, mostrar, "
                                + "editar y eliminar las mismas. La API está documentada utilizando "
                                + "Swagger, lo que facilita la comprensión de los endpoints y su uso.")
                )
                .servers(List.of(new Server()
                        .url("http://localhost:8080/")
                        .description("Servidor Local")));

    }

}
