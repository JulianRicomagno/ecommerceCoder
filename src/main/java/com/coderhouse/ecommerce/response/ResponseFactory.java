package com.coderhouse.ecommerce.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseFactory {
    ResponseEntity<?> createResponse(Object data, String message, HttpStatus status);
}
