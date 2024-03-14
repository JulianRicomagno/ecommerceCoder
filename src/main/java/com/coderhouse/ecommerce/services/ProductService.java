package com.coderhouse.ecommerce.services;

import java.util.List;
import com.coderhouse.ecommerce.entity.Product;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    Product findById(Long id);
    void delete(Product product);
}
