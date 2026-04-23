package com.acm.ecommerce.mapper;

import com.acm.ecommerce.dto.OrderDTO;
import com.acm.ecommerce.entities.OrderEntity;
import com.acm.ecommerce.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO.OrderResponse toResponse(OrderEntity orderEntity);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderDTO.OrderItemResponse toOrderItemResponse(OrderItemEntity item);
}
