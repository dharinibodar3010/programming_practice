package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleNotFound(
            ResourceNotFoundException ex){

        Map<String,Object> map = new HashMap<>();

        map.put("status",404);
        map.put("message",ex.getMessage());

        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleException(
            Exception ex){

        Map<String,Object> map = new HashMap<>();

        map.put("status",500);
        map.put("message",ex.getMessage());

        return map;
    }
}