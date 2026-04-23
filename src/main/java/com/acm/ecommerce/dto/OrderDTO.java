package com.acm.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    public record OrderRequest(
            Long userId,
            List<OrderItemRequest> items
    ) {}

    public record OrderItemRequest(
            Long productId,
            Integer quantity
    ) {}

    public record OrderResponse(
            Long id,
            Long userId,
            LocalDateTime createdAt,
            Double totalAmount,
            List<OrderItemResponse> items
    ) {}

    public record OrderItemResponse(
            Long id,
            Long productId,
            String productName,
            Integer quantity,
            Double price
    ) {}
}
