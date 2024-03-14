package com.coderhouse.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Puedes agregar métodos personalizados aquí si es necesario
}
