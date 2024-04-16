package com.coderhouse.ecommerce.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Product;

@Repository
@Hidden
public interface ProductsRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);

}

