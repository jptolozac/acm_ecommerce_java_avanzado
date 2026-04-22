package com.acm.ecommerce.services;

import com.acm.ecommerce.dto.ProductDTO;
import com.acm.ecommerce.entities.ProductEntity;
import com.acm.ecommerce.mapper.ProductMapper;
import com.acm.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public List<ProductDTO.ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductDTO.ProductResponse create(ProductDTO.ProductRequest productRequest) {
        ProductEntity product = productMapper.toEntity(productRequest);
        product = repository.save(product);
        return productMapper.toResponse(product);
    }

    public ProductDTO.ProductResponse update(Long id, ProductDTO.ProductRequest productRequest) {
        ProductEntity existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productMapper.updateEntityFromRequest(productRequest, existingProduct);
        ProductEntity updatedProduct = repository.save(existingProduct);
        return productMapper.toResponse(updatedProduct);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<ProductEntity> findById(Long id){
        return repository.findById(id);
    }

}
