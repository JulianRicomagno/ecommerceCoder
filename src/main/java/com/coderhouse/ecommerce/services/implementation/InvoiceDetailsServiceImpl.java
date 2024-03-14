package com.coderhouse.ecommerce.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.InvoiceDetail;
import com.coderhouse.ecommerce.repository.InvoiceDetailsRepository;
import com.coderhouse.ecommerce.services.InvoiceDetailsService;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsDao;

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetailsDao.findAll();
    }

    @Override
    public InvoiceDetail save(InvoiceDetail invoiceDetails) {
        return invoiceDetailsDao.save(invoiceDetails);
    }

    @Override
    public InvoiceDetail findById(Long id) {
        return invoiceDetailsDao.findById(id).orElse(null);
    }

    @Override
    public void delete(InvoiceDetail invoiceDetails) {
        invoiceDetailsDao.delete(invoiceDetails);
    }
}
