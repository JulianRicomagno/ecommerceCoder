package com.coderhouse.ecommerce.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultResponseFactory implements ResponseFactory {

    @Override
    public ResponseEntity<?> createResponse(Object data, String message, HttpStatus status) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", status == HttpStatus.OK || status == HttpStatus.CREATED);
        res.put("message", message);
        res.put("data", data);
        return ResponseEntity.status(status).body(res);
    }
}
