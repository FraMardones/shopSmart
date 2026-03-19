package com.example.ShopSmart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Este método atrapa cualquier error inesperado y devuelve un mensaje limpio
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionesGenerales(Exception ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Ha ocurrido un error interno en el servidor.");
        respuesta.put("detalle", ex.getMessage());
        
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}