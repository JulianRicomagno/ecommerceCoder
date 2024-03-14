package com.coderhouse.ecommerce.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Invoice;
import com.coderhouse.ecommerce.repository.InvoiceRepository;
import com.coderhouse.ecommerce.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceDao;

    @Override
    public List<Invoice> findAll() {
        return invoiceDao.findAll();
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceDao.delete(invoice);
    }
}
