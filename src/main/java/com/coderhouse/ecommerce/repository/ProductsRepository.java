package com.coderhouse.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

}

