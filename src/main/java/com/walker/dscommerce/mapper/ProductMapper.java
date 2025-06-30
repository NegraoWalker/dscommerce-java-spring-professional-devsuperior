package com.walker.dscommerce.mapper;

import com.walker.dscommerce.domain.model.Product;
import com.walker.dscommerce.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMapper {
    @Autowired
    private static ModelMapper modelMapper;

    public static ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public static Product toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
