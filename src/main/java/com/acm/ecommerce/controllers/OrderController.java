package com.acm.ecommerce.controllers;

import com.acm.ecommerce.dto.OrderDTO;
import com.acm.ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO.OrderResponse> createOrder(@RequestBody OrderDTO.OrderRequest request) {
        OrderDTO.OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDTO.OrderResponse>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderDTO.OrderResponse> responses = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(responses);
    }
}
