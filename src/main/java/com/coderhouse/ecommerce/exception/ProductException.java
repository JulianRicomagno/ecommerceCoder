package com.coderhouse.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ProductAlreadyExistsException extends RuntimeException {
        public ProductAlreadyExistsException(String code) {
            super("El producto con el codigo '" + code + "' ya existe");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String id) {
            super("No se encontró el producto con ID: " + id);
        }
    }

}
