package com.walker.dscommerce.controller;

import com.walker.dscommerce.dto.ProductDTO;
import com.walker.dscommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    //Dependências:
    private final ProductService productService;

    // Construtor para injeção de dependência
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> productDTOPage = productService.findAll(pageable);
        return ResponseEntity.ok(productDTOPage);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.insert(productDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProductDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updateProductDTO = productService.update(id, productDTO);
        return ResponseEntity.ok(updateProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


//    @GetMapping
//    public String ping() {
//        return "pong!";
//    }
}
