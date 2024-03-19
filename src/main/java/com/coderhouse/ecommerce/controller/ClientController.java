package com.coderhouse.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        Client existingClient = clientService.findByDocnumber(client.getDocnumber());
        if (existingClient != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya se encuentra creado un usuario con ese DNI");
        }
        Client savedClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente");
    }


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClientNameAndLastName(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client existingClient = clientService.findById(id);
        if (existingClient != null) {
            existingClient.setName(updatedClient.getName());
            existingClient.setLastname(updatedClient.getLastname());
            Client savedClient = clientService.save(existingClient);
            return ResponseEntity.ok(savedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client != null) {
            clientService.delete(client);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
