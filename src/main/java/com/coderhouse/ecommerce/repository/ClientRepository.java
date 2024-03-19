package com.coderhouse.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByDocnumber(String docnumber);
}
