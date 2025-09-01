package com.walker.dscommerce.service;

import com.walker.dscommerce.dto.ProductDTO;
import com.walker.dscommerce.dto.mapper.ProductMapper;
import com.walker.dscommerce.model.Product;
import com.walker.dscommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    //Dependências:
    private final ProductRepository productRepository; // Injeção do nosso repository
    private final ProductMapper productMapper; // Injeção do nosso mapper


    //Construtor para injeção de dependência
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    /**
     * Busca um produto por ID e retorna como DTO.
     * Note como a conversão fica limpa e clara.
     */
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product não encontrado com ID: " + id));
        return productMapper.toDTO(product); // A mágica acontece aqui: conversão automática!
    }
}
