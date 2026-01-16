package com.walker.dscommerce.controller;

import com.walker.dscommerce.model.dto.CategoryDTO;
import com.walker.dscommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        return ResponseEntity.ok(categoryDTOS);
    }

}
