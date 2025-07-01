package com.walker.dscommerce.mapper;

import com.walker.dscommerce.domain.model.Product;
import com.walker.dscommerce.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
