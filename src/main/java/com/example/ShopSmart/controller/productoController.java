package com.example.ShopSmart.controller;

import com.example.ShopSmart.model.producto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class productoController {

    // Lista en memoria para simular una base de datos
    private List<producto> inventario = new ArrayList<>();

    public productoController() {
        // Añadimos algunos productos iniciales para las pruebas
        inventario.add(new producto(1L, "Laptop Gamer", 1200.50, 15));
        inventario.add(new producto(2L, "Auriculares Inalámbricos", 85.00, 50));
    }

    // Endpoint 1: GET /productos
    @GetMapping
    public ResponseEntity<List<producto>> obtenerProductos() {
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    // Endpoint 2: POST /productos
    @PostMapping
    public ResponseEntity<producto> agregarProducto(@RequestBody producto nuevoProducto) {
        // Simulamos la generación de un ID único
        nuevoProducto.setId((long) (inventario.size() + 1));
        inventario.add(nuevoProducto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
}
