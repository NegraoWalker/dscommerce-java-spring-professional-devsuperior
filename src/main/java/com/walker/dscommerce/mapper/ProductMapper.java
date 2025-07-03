package com.walker.dscommerce.mapper;

import com.walker.dscommerce.domain.model.Product;
import com.walker.dscommerce.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProductDto> toDtoList(List<Product> products) {
        List<ProductDto> dtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto dto = toDto(product); // converte produto para DTO
            dtoList.add(dto); // adiciona na lista de retorno
        }
        return dtoList;
    }

    public List<Product> toEntityList(List<ProductDto> productDtoList) {
        List<Product> entityList = new ArrayList<>();
        for (ProductDto dto : productDtoList) {
            Product entity = toEntity(dto); // converte DTO para entidade
            entityList.add(entity); // adiciona na lista de retorno
        }
        return entityList;
    }

    public Page<ProductDto> toDtoPage(Page<Product> productPage) {
        List<ProductDto> dtoList = toDtoList(productPage.getContent());
        return new PageImpl<>(dtoList, productPage.getPageable(), productPage.getTotalElements());
    }


}
