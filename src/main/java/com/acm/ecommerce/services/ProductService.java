package com.acm.ecommerce.services;

import com.acm.ecommerce.dto.ProductDTO;
import com.acm.ecommerce.entities.ProductEntity;
import com.acm.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<ProductDTO.ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(p -> new ProductDTO.ProductResponse(p.getId(), p.getName(),p.getPrice(), p.getStock()))
                .toList();
    }

    public ProductDTO.ProductResponse create(ProductDTO.ProductRequest request) {
        ProductEntity product = new ProductEntity();
        product = repository.save(product);
        return new ProductDTO.ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<ProductEntity> findById(Long id){
        return repository.findById(id);
    }




}
