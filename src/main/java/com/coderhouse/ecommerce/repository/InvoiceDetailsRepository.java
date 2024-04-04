package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.coderhouse.ecommerce.entity.InvoiceDetail;

import java.util.List;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetail, Long> {

    List<InvoiceDetail> findByInvoice(Invoice invoiceId);
}
