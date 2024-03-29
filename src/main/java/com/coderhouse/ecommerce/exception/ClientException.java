package com.coderhouse.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClientException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ClientAlreadyExistsException extends RuntimeException {
        public ClientAlreadyExistsException(String docnumber) {
            super("El cliente con el número de documento '" + docnumber + "' ya existe");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException(Long id) {
            super("No se encontró el cliente con ID: " + id);
        }
    }

}
