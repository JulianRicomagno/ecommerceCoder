package com.coderhouse.ecommerce.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.ecommerce.response.ResponseFactory;
import com.coderhouse.ecommerce.entity.Invoice;
import com.coderhouse.ecommerce.entity.requestBody.RequestInvoice;
import com.coderhouse.ecommerce.services.implementation.InvoiceServiceImpl;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    private ResponseFactory responseFactory;

    @GetMapping
    public ResponseEntity<?> getAllInvoices() {
        try {
            List<Map<String, Object>> invoices = invoiceService.getAll();
            return responseFactory.createResponse(invoices, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        try {
            Map<String, Object> savedInvoice = invoiceService.getInvoiceById(id);
            return responseFactory.createResponse(savedInvoice, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody RequestInvoice invoice) {
        try {
            Map<String, Object> savedInvoice = invoiceService.saveInvoice(invoice);
            return responseFactory.createResponse(savedInvoice, "Factura creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
