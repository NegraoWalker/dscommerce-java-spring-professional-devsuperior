package com.walker.dscommerce.controller;

import com.walker.dscommerce.dto.ProductDTO;
import com.walker.dscommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        Page<ProductDTO> productDTOPage = productService.findAll(name, pageable);
        return ResponseEntity.ok(productDTOPage);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.insert(productDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProductDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdProductDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updateProductDTO = productService.update(id, productDTO);
        return ResponseEntity.ok(updateProductDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
