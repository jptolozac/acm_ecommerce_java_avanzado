package com.acm.ecommerce.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Usuarios", description = "CRUD para gestionar usuarios")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

}
