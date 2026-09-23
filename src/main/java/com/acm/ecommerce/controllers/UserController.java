package com.acm.ecommerce.controllers;

import com.acm.ecommerce.dto.UserDTO;
import com.acm.ecommerce.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "CRUD para gestionar usuarios")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @Operation(summary = "Obtener lista de usuarios", description = "Retorna todos los usuarios excepto los eliminados")
    @GetMapping
    public ResponseEntity<List<UserDTO.UserResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Create a new user", description = "Creo que es bastante obvio xd creamos un usuario")
    @GetMapping
    public ResponseEntity<UserDTO.UserResponse> create(@RequestBody UserDTO.UserRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Updated an user", description = "An user is updated")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO.UserResponse> update(@PathVariable Long id, @RequestBody UserDTO.UserRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminación Lógica", description = "Se elimina un usuario de forma lógica, no permanete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
