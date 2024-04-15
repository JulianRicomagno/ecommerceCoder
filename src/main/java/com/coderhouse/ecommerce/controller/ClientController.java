package com.coderhouse.ecommerce.controller;

import java.util.List;

import com.coderhouse.ecommerce.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.services.implementation.ClientServiceImpl;


@RestController
@RequestMapping("api/clients")
@Tag(name= "API Clientes", description = "Endpoints para Gestionar Clientes")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ResponseFactory responseFactory;

    @Operation(summary = "Obtener lista de Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cliente obtenida correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clients = clientService.findAll();
            return responseFactory.createResponse(clients, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Guardar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente guardado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createClient(@Valid @RequestBody @Schema(description = "Objeto JSON que representa un cliente", example = "{\"name\":\"Gonzalo\",\"lastname\":\"Rivas\",\"docnumber\":\"16789\"}") Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseFactory.createResponse(null, "Revise la informacion enviada que no es correcta", HttpStatus.BAD_REQUEST);
        }
        try {
            Client savedClient = clientService.saveClient(client);
            return responseFactory.createResponse(savedClient, "Cliente creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obtener Cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente obtenido correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            Client client = clientService.getClientById(id);
            return responseFactory.createResponse(client, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar Cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateClientNameAndLastName(@PathVariable Long id, @Valid @RequestBody @Schema(description = "Objeto JSON que representa un cliente", example = "{\"name\":\"Gonzalo\",\"lastname\":\"Rivas\",\"docnumber\":\"16789\"}") Client updatedClient
    ) {
        try {
            Client client = clientService.updateClient(id, updatedClient);
            return responseFactory.createResponse(client, "Cliente actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Eliminar Cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            Client client = clientService.deleteClient(id);
            return responseFactory.createResponse(client, "Cliente borrado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
