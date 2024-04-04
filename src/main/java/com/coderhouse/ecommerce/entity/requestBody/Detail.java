package com.coderhouse.ecommerce.entity.requestBody;

import java.math.BigDecimal;

public class Detail {

    private Long invoiceId;
    private int amount;
    private Long productId;
    private Double price;

    public Detail() {
    }

    public Detail(Long invoiceId, int amount, Long productId, Double price) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.productId = productId;
        this.price = price;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "invoiceId=" + invoiceId +
                ", amount=" + amount +
                ", productId=" + productId +
                ", price=" + price +
                '}';
    }
}
