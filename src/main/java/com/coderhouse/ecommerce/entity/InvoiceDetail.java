package com.coderhouse.ecommerce.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private Double price;

    // Getters and setters
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getInvoiceDetailId() {
        return id;
    }

    public void setInvoiceDetailId(Long invoiceDetailId) {
        this.id = invoiceDetailId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, Object> getInvoiceDetail() {
        Map<String, Object> invoiceDetail = new HashMap<>();
        invoiceDetail.put("productCode", this.product.getCode());
        invoiceDetail.put("productDescription", this.product.getDescription());
        invoiceDetail.put("amount", this.amount);
        invoiceDetail.put("price", this.price);
        return invoiceDetail;
    }
}