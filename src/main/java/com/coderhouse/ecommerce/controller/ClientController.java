package com.coderhouse.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.services.ClientService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> getAllClients() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Client> clients = clientService.findAll();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client client = clientService.findById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client createdClient = clientService.save(client);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client existingClient = clientService.findById(id);
            existingClient.setName(client.getName());
            existingClient.setLastname(client.getLastname());
            existingClient.setDocnumber(client.getDocnumber());

            Client updatedClient = clientService.save(existingClient);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client client = clientService.findById(id);
            clientService.delete(client);
            response.put("deleted", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
