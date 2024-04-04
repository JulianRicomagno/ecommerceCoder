package com.coderhouse.ecommerce.entity.requestBody;

import java.math.BigDecimal;
import java.util.List;

public class RequestInvoice {

    private Long id;
    private Long client;
    private Double total;
    private List<Detail> detail;

    public RequestInvoice() {
    }

    public RequestInvoice(Long id, Long client, Double total, List<Detail> detail) {
        this.id = id;
        this.client = client;
        this.total = total;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "RequestInvoice{" +
                "id=" + id +
                ", client=" + client +
                ", total=" + total +
                ", detail=" + detail +
                '}';
    }
}
