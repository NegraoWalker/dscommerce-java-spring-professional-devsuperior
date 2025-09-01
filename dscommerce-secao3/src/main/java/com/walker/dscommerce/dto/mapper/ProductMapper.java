package com.walker.dscommerce.dto.mapper;

import com.walker.dscommerce.dto.ProductDTO;
import com.walker.dscommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interface que define as conversões entre Product e ProductDTO.
 * O MapStruct gerará automaticamente a implementação desta interface.
 */
@Mapper(componentModel = "spring") // Integração com Spring - permitirá @Autowired
public interface ProductMapper {

    /**
     * Converte uma entidade Product para ProductDTO.
     * Como os nomes dos campos são idênticos, o MapStruct fará o mapeamento automaticamente.
     */
    ProductDTO toDTO(Product product);

    /**
     * Converte um ProductDTO para entidade Product.
     * Note que ignoramos relacionamentos complexos na conversão reversa.
     */
    @Mapping(target = "categories", ignore = true) // Ignoramos categories pois não estão no DTO
    @Mapping(target = "orderItems", ignore = true) // Ignoramos orderItems pois não estão no DTO
    Product toEntity(ProductDTO productDTO);

    /**
     * Converte uma lista de Products para lista de ProductDTOs.
     * O MapStruct automaticamente usa o método toDTO para cada item.
     */
    List<ProductDTO> toDTOList(List<Product> products);

    /**
     * Converte uma lista de ProductDTOs para lista de Products.
     * O MapStruct automaticamente usa o método toEntity para cada item.
     */
    List<Product> toEntityList(List<ProductDTO> productDTOs);
}
