package com.coderhouse.ecommerce.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Client;

@Repository
@Hidden
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByDocnumber(String docnumber);
}
