package com.coderhouse.ecommerce.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.Invoice;

@Repository
@Hidden
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
