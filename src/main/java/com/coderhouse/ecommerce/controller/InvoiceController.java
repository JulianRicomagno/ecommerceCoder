package com.coderhouse.ecommerce.controller;

import java.util.*;

import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name= "API Facturas", description = "Endpoints para Gestionar Facturas")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    private ResponseFactory responseFactory;

    @Operation(summary = "Obtener lista de Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getAllInvoices() {
        try {
            List<Map<String, Object>> invoices = invoiceService.getAll();
            return responseFactory.createResponse(invoices, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obtener Factura por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura obtenido correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        try {
            Map<String, Object> savedInvoice = invoiceService.getInvoiceById(id);
            return responseFactory.createResponse(savedInvoice, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Guardar Factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura guardado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createInvoice(
            @Valid @RequestBody @Schema(
                    description = "Objeto JSON que representa un producto",
                    example = "{\"id\":\"1\",\"client\":\"2\",\"total\":\"500\",\"detail\": [{\"amount\":\"2\",\"productId\":\"1\",\"price\":\"500\"}]}") RequestInvoice invoice) {
        try {
            Map<String, Object> savedInvoice = invoiceService.saveInvoice(invoice);
            return responseFactory.createResponse(savedInvoice, "Factura creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
