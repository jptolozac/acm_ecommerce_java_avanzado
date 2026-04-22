package com.acm.ecommerce.controllers;

import com.acm.ecommerce.dto.ProductDTO;
import com.acm.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO.ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')") lo dejo comentado porque aun no tengo la parte de seguridad
    public ResponseEntity<ProductDTO.ProductResponse> create(@RequestBody ProductDTO.ProductRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
