package com.coderhouse.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.InvoiceDetail;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetail, Long> {

    // Puedes agregar métodos personalizados aquí si es necesario
}
