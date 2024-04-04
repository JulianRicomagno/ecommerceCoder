package com.coderhouse.ecommerce.services;

import java.util.List;

import com.coderhouse.ecommerce.entity.Invoice;
import com.coderhouse.ecommerce.entity.InvoiceDetail;

public interface InvoiceDetailsService {
    List<InvoiceDetail> findAll();
    InvoiceDetail save(InvoiceDetail invoiceDetails);
    InvoiceDetail findById(Long id);
    void delete(InvoiceDetail invoiceDetails);
    List<InvoiceDetail> findByInvoice (Invoice invoice);
}
