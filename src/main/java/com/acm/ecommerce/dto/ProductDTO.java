package com.acm.ecommerce.dto;

public class ProductDTO {

    public record ProductRequest(
            String name,
            String description,
            Double price,
            Integer stock
    ) {}

    public record ProductResponse(
            Long id,
            String name,
            Double price,
            Integer stock
    ) {}
}
