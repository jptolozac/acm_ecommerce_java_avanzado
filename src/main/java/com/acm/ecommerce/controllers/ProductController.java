package com.acm.ecommerce.controllers;

import com.acm.ecommerce.dto.ProductDTO;
import com.acm.ecommerce.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Productos", description = "CRUD para gestión de inventario")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }
    @Operation(summary = "Obtener lista de productos", description = "Retorna todos los productos que no han sido eliminados lógicamente.")
    @GetMapping
    public ResponseEntity<List<ProductDTO.ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @Operation(summary = "Crear nuevo producto", description = "Se crean nuevos productos.")
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')") lo dejo comentado porque aun no tengo la parte de seguridad
    public ResponseEntity<ProductDTO.ProductResponse> create(@RequestBody ProductDTO.ProductRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminación Soft", description = "Se eliminan productos.")
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
