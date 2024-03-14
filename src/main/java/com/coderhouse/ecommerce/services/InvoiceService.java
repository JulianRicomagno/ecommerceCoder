package com.coderhouse.ecommerce.services;

import java.util.List;
import com.coderhouse.ecommerce.entity.Invoice;

public interface InvoiceService {
    List<Invoice> findAll();
    Invoice save(Invoice invoice);
    Invoice findById(Long id);
    void delete(Invoice invoice);
}
