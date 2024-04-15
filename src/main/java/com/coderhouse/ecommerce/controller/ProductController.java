package com.coderhouse.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.services.implementation.ProductServiceImpl;
import com.coderhouse.ecommerce.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name= "API Productos", description = "Endpoints para Gestionar Productos")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ResponseFactory responseFactory;

    //Create a product
    @Operation(summary = "Guardar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente guardado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createProduct(@Valid @RequestBody @Schema(description = "Objeto JSON que representa un producto", example = "{\"description\":\"Camisa de algodón\",\"code\":\"ghjk123\",\"price\":\"29.99\",\"stock\":\"100\"}") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseFactory.createResponse(null, "Revise la informacion enviada que no es correcta", HttpStatus.BAD_REQUEST);
        }
        try {
            Product createdProduct = productService.saveProduct(product);
            return responseFactory.createResponse(createdProduct, "Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET all products
    @Operation(summary = "Obtener lista de Productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return responseFactory.createResponse(products, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET product by code
    @Operation(summary = "Obtener Producto por Codigo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/code/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getProductByCode(@PathVariable String code) {
        try {
            Product product = productService.getProductByCode(code);
            return responseFactory.createResponse(product, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET product by id
    @Operation(summary = "Obtener Producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return responseFactory.createResponse(product, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //PUT a product
    @Operation(summary = "Actualizar Producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
    @Valid @RequestBody @Schema(description = "Objeto JSON que representa un producto", example = "{\"stock\":\"10\"}") Product updatedProduct
    ) {
        try {
            Product product = productService.updateProduct(id, updatedProduct);
            return responseFactory.createResponse(product, "Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE a product
    @Operation(summary = "Eliminar Producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            Product product = productService.deleteProduct(id);
            return responseFactory.createResponse(product, "Producto borrado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
