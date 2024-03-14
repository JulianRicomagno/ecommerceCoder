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

import com.coderhouse.ecommerce.entity.InvoiceDetail;
import com.coderhouse.ecommerce.services.InvoiceDetailsService;

@RestController
@RequestMapping("/api/v1")
public class InvoiceDetailsController {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @GetMapping("/invoice-details")
    public ResponseEntity<Object> getAllInvoiceDetails() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<InvoiceDetail> invoiceDetailsList = invoiceDetailsService.findAll();
            return new ResponseEntity<>(invoiceDetailsList, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice-details/{id}")
    public ResponseEntity<Object> getInvoiceDetailsById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            InvoiceDetail invoiceDetails = invoiceDetailsService.findById(id);
            return new ResponseEntity<>(invoiceDetails, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invoice-details")
    public ResponseEntity<Object> createInvoiceDetails(@RequestBody InvoiceDetail invoiceDetails) {
        Map<String, Object> response = new HashMap<>();
        try {
            InvoiceDetail createdInvoiceDetails = invoiceDetailsService.save(invoiceDetails);
            return new ResponseEntity<>(createdInvoiceDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoice-details/{id}")
    public ResponseEntity<Object> updateInvoiceDetails(@RequestBody InvoiceDetail invoiceDetails, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            InvoiceDetail existingInvoiceDetails = invoiceDetailsService.findById(id);
            // Actualizar los campos necesarios de existingInvoiceDetails con los valores de invoiceDetails

            InvoiceDetail updatedInvoiceDetails = invoiceDetailsService.save(existingInvoiceDetails);
            return new ResponseEntity<>(updatedInvoiceDetails, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/invoice-details/{id}")
    public ResponseEntity<Object> deleteInvoiceDetails(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            InvoiceDetail invoiceDetails = invoiceDetailsService.findById(id);
            invoiceDetailsService.delete(invoiceDetails);
            response.put("deleted", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
