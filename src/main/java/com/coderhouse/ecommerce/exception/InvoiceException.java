package com.coderhouse.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvoiceException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvoiceUnavailableCreate extends RuntimeException {
        public InvoiceUnavailableCreate() {
            super("No es posible generar la factura ya que hay productos que no tienen stock sufiiente");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class InvoiceNotFoundException extends RuntimeException {
        public InvoiceNotFoundException(Long id) {
            super("No se encontró la factura con ID: " + id);
        }
    }
}

