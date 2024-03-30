package com.coderhouse.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre es requerida")
    @Size(max = 255, message = "El nombre debe tener como máximo 255 caracteres")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "El apellido es requerida")
    @Size(max = 255, message = "El apellido debe tener como máximo 255 caracteres")
    @Column(name = "lastname")
    private String lastname;

    @NotNull(message = "El DNI es requerido")
    @PositiveOrZero(message = "El DNI debe ser un número positivo o cero")
    @Column(name = "docnumber")
    private String docnumber;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }
}


