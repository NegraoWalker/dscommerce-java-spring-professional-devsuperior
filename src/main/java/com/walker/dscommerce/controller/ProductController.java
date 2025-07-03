package com.walker.dscommerce.controller;

import com.walker.dscommerce.dto.ProductDto;
import com.walker.dscommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable(value = "id") Long id) {
        return productService.findById(id);
    }

    @GetMapping()
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

}
