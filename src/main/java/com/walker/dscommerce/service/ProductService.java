package com.walker.dscommerce.service;

import com.walker.dscommerce.domain.model.Product;
import com.walker.dscommerce.dto.ProductDto;
import com.walker.dscommerce.mapper.ProductMapper;
import com.walker.dscommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        return productMapper.toDto(result);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return productMapper.toDtoPage(result);
    }
}

