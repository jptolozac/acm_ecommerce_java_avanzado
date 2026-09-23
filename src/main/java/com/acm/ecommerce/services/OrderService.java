package com.acm.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acm.ecommerce.dto.OrderDTO;
import com.acm.ecommerce.entities.OrderEntity;
import com.acm.ecommerce.entities.OrderItemEntity;
import com.acm.ecommerce.entities.ProductEntity;
import com.acm.ecommerce.mapper.OrderMapper;
import com.acm.ecommerce.repository.OrderRepository;
import com.acm.ecommerce.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO.OrderResponse createOrder(OrderDTO.OrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setUserId(request.userId());

        double totalAmount = 0.0;

        for (OrderDTO.OrderItemRequest itemRequest : request.items()) {
            ProductEntity product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemRequest.productId()));

            if (product.getStock() < itemRequest.quantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName() + ". Available: " + product.getStock());
            }

            product.setStock(product.getStock() - itemRequest.quantity());

            productRepository.save(product);

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .price(product.getPrice())
                    .build();

            order.getItems().add(orderItem);
            totalAmount += product.getPrice() * itemRequest.quantity();
        }

        order.setTotalAmount(totalAmount);

        OrderEntity savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    public List<OrderDTO.OrderResponse> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
