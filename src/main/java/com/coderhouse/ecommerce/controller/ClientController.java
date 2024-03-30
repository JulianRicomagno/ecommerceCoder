package com.coderhouse.ecommerce.controller;

import java.util.List;

import com.coderhouse.ecommerce.response.ResponseFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.services.implementation.ClientServiceImpl;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
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

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clients = clientService.findAll();
            return responseFactory.createResponse(clients, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            Client client = clientService.getClientById(id);
            return responseFactory.createResponse(client, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientNameAndLastName(@PathVariable Long id, @RequestBody Client updatedClient) {
        try {
            Client client = clientService.updateClient(id, updatedClient);
            return responseFactory.createResponse(client, "Cliente actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            Client client = clientService.deleteClient(id);
            return responseFactory.createResponse(client, "Cliente borrado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
