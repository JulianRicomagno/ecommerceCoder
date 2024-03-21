package com.coderhouse.ecommerce.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.repository.ProductsRepository;
import com.coderhouse.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productDao;

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
}
