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

import com.coderhouse.ecommerce.entity.Invoice;
import com.coderhouse.ecommerce.services.InvoiceService;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public ResponseEntity<Object> getAllInvoices() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Invoice> invoices = invoiceService.findAll();
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Invoice invoice = invoiceService.findById(id);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invoices")
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice) {
        Map<String, Object> response = new HashMap<>();
        try {
            Invoice createdInvoice = invoiceService.save(invoice);
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<Object> updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Invoice existingInvoice = invoiceService.findById(id);
            // Actualizar los campos necesarios de existingInvoice con los valores de invoice

            Invoice updatedInvoice = invoiceService.save(existingInvoice);
            return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Invoice invoice = invoiceService.findById(id);
            invoiceService.delete(invoice);
            response.put("deleted", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
