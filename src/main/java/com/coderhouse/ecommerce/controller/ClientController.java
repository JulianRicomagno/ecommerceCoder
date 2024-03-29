package com.coderhouse.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.services.implementation.ClientServiceImpl;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        Map<String, Object> res = new HashMap<>();
        try {
            Client savedClient = clientService.save(client);
            res.put("succes", true);
            res.put("message", "Cliente creado exitosamente");
            res.put("data", savedClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            res.put("succes", false);
            res.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllClients() {
        Map<String, Object> res = new HashMap<>();
        try {
            List<Client> clients = clientService.findAll();
            res.put("succes", true);
            res.put("data", clients);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            res.put("succes", false);
            res.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Client client = clientService.getClientById(id);
            res.put("succes", true);
            res.put("data", client);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            res.put("succes", false);
            res.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientNameAndLastName(@PathVariable Long id, @RequestBody Client updatedClient) {
        Map<String, Object> res = new HashMap<>();
        try {
            Client client = clientService.updateClient(id, updatedClient);
            res.put("succes", true);
            res.put("message", "Cliente actualizado exitosamente");
            res.put("data", client);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            res.put("succes", false);
            res.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Client client = clientService.deleteClient(id);
            res.put("succes", true);
            res.put("message", "Cliente borrado exitosamente");
            res.put("data", client);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            res.put("succes", false);
            res.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
