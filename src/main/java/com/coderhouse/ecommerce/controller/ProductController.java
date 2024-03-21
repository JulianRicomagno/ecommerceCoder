package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Crear un producto
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        // Verificar si el código ya existe
        if (productService.findByCode(product.getCode()) != null) {
            return new ResponseEntity<>("El código del producto ya existe", HttpStatus.BAD_REQUEST);
        }
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    // GET de todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // GET de producto por code
    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        Product product = productService.findByCode(code);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // GET de producto por id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // PUT de description, stock, price segun lo que se envia que puede ser todo uno o dos
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Actualizar solo los campos que se envían en el cuerpo de la solicitud
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getStock() != null) {
            existingProduct.setStock(updatedProduct.getStock());
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        Product savedProduct = productService.save(existingProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    // DELETE de producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
