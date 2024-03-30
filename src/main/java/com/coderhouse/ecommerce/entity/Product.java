package com.coderhouse.ecommerce.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "La descripción es requerida")
    @Size(max = 255, message = "La descripción debe tener como máximo 255 caracteres")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "El código es requerido")
    @Size(max = 20, message = "El código debe tener como máximo 20 caracteres")
    @Column(name = "code")
    private String code;

    @NotNull(message = "El stock es requerido")
    @PositiveOrZero(message = "El stock debe ser un número positivo o cero")
    @Column(name = "stock")
    private Integer stock;

    @NotNull(message = "El precio es requerido")
    @PositiveOrZero(message = "El precio debe ser un número positivo o cero")
    @Column(name = "price")
    private Double price;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
