package com.coderhouse.ecommerce.controller;

import jakarta.validation.Valid;
import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.services.implementation.ProductServiceImpl;
import com.coderhouse.ecommerce.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ResponseFactory responseFactory;

    //Create a product
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
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
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return responseFactory.createResponse(products, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET product by code
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable String code) {
        try {
            Product product = productService.getProductByCode(code);
            return responseFactory.createResponse(product, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET product by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return responseFactory.createResponse(product, null, HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //PUT a product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(id, updatedProduct);
            return responseFactory.createResponse(product, "Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE a product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            Product product = productService.deleteProduct(id);
            return responseFactory.createResponse(product, "Producto borrado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return responseFactory.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
