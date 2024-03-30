package com.coderhouse.ecommerce.services.implementation;

import java.util.List;

import com.coderhouse.ecommerce.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.repository.ProductsRepository;
import com.coderhouse.ecommerce.services.ProductService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productDao;

    private final Validator validator;

    public ProductServiceImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product findByCode(String code) {
        return productDao.findByCode(code);
    }

    public Product saveProduct(Product product) {
        Product existingProduct = this.findByCode(product.getCode());
        if (existingProduct != null) {
            throw new ProductException.ProductAlreadyExistsException(product.getCode());
        }
        return this.save(product);
    }

    public Product getProductByCode(String code) {
        Product product = this.findByCode(code);
        if (product == null) {
            throw new ProductException.ProductNotFoundException(code);
        }
        return product;
    }

    public Product getProductById(Long id) {
        Product product = this.findById(id);
        if (product == null) {
            throw new ProductException.ProductNotFoundException(String.valueOf(id));
        }
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = this.findById(id);
        if (product == null) {
            throw new ProductException.ProductNotFoundException(String.valueOf(id));
        }
        if (updatedProduct.getDescription() != null) {
            product.setDescription(updatedProduct.getDescription());
            validateAndThrow(product);
        }
        if (updatedProduct.getStock() != null) {
            product.setStock(updatedProduct.getStock());
            validateAndThrow(product);
        }
        if (updatedProduct.getPrice() != null) {
            product.setPrice(updatedProduct.getPrice());
            validateAndThrow(product);
        }
        return this.save(product);
    }

    // Método para validar y lanzar una excepción si hay violaciones de restricciones
    private void validateAndThrow(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Product> violation : violations) {
                errorMessage.append(violation.getPropertyPath())
                        .append(": ")
                        .append(violation.getMessage())
                        .append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public Product deleteProduct(Long id) {
        Product product = this.findById(id);
        if (product == null) {
            throw new ProductException.ProductNotFoundException(String.valueOf(id));
        }
        this.delete(product);
        return product;
    }
}
