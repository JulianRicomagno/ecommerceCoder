package com.coderhouse.ecommerce.services.implementation;

import java.util.*;

import com.coderhouse.ecommerce.entity.InvoiceDetail;
import com.coderhouse.ecommerce.entity.Product;
import com.coderhouse.ecommerce.entity.requestBody.Detail;
import com.coderhouse.ecommerce.entity.requestBody.RequestInvoice;
import com.coderhouse.ecommerce.exception.InvoiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Invoice;
import com.coderhouse.ecommerce.repository.InvoiceRepository;
import com.coderhouse.ecommerce.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceDao;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private InvoiceDetailsServiceImpl invoiceDetailsService;

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

    public Map<String, Object> saveInvoice(RequestInvoice invoice) {
        List<InvoiceDetail> details = this.validateInvoiceDetail(invoice.getDetail());
        Invoice savedInvoice = this.createInvoice(invoice);
        List<InvoiceDetail> savedDetails = this.createDetail(savedInvoice, details);
        return getInvoiceSaved(savedInvoice, savedDetails);
    }

    public Map<String, Object> getInvoiceSaved(Invoice savedInvoice, List<InvoiceDetail> savedDetails) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> responseDetail = new ArrayList<>();
        response.put("invoice", savedInvoice);
        for (InvoiceDetail myDetail: savedDetails) {
            responseDetail.add(myDetail.getInvoiceDetail());
        }
        response.put("detail", responseDetail);
        return response;
    }

    public List<InvoiceDetail> createDetail(Invoice savedInvoice, List <InvoiceDetail> details) {
        for (InvoiceDetail myDetail: details) {
            myDetail.setInvoice(savedInvoice);
            invoiceDetailsService.save(myDetail);
        }
        return details;
    }

    public Invoice createInvoice(RequestInvoice invoice) {
        Invoice myInvoice = new Invoice();
        myInvoice.setClient(clientService.getClientById(invoice.getClient()));
        myInvoice.setCreatedAt(new Date());
        myInvoice.setTotal(invoice.getTotal());
        return this.save(myInvoice);
    }

    public List<InvoiceDetail> validateInvoiceDetail(List<Detail> myInvoiceDetails) {
        List<InvoiceDetail> detail = new ArrayList<>();
        for (Detail invoiceDetail: myInvoiceDetails){
            Product myProduct = productService.getProductById(invoiceDetail.getProductId());
            if (myProduct.getStock()-invoiceDetail.getAmount() < 0) {
                throw new InvoiceException.InvoiceUnavailableCreate();
            }
            InvoiceDetail myDetail = new InvoiceDetail();
            myDetail.setAmount(invoiceDetail.getAmount());
            myDetail.setPrice(invoiceDetail.getPrice());
            myDetail.setProduct(myProduct);
            detail.add(myDetail);
        }
        return detail;
    }

    public Map<String, Object> getInvoiceById(Long id){
        Invoice targetInvoice = this.findById(id);
        if (targetInvoice == null) {
            throw new InvoiceException.InvoiceNotFoundException(id);
        }
        List<InvoiceDetail> InvoiceDetail = invoiceDetailsService.findByInvoice(targetInvoice);
        return this.getInvoiceSaved(targetInvoice, InvoiceDetail);
    }

    public List<Map<String, Object>> getAll(){
        List<Invoice> targetInvoices = this.findAll();
        List<Map<String, Object>> responseDetail = new ArrayList<>();
        for (Invoice myInvoice: targetInvoices) {
            List<InvoiceDetail> InvoiceDetail = invoiceDetailsService.findByInvoice(myInvoice);
            responseDetail.add(this.getInvoiceSaved(myInvoice, InvoiceDetail));
        }
        return responseDetail;
    }

}
