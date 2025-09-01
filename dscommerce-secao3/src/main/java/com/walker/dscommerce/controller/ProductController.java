package com.walker.dscommerce.controller;

import com.walker.dscommerce.dto.ProductDTO;
import com.walker.dscommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    //Dependências:
    private final ProductService productService;

    // Construtor para injeção de dependência
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint para buscar produto por ID.
     * Retorna apenas os dados seguros via DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }







//    @GetMapping
//    public String ping() {
//        return "pong!";
//    }
}
