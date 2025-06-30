package com.walker.dscommerce.service;

import com.walker.dscommerce.domain.model.Product;
import com.walker.dscommerce.dto.ProductDto;
import com.walker.dscommerce.mapper.ProductMapper;
import com.walker.dscommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        return ProductMapper.toDto(result);
    }
}

