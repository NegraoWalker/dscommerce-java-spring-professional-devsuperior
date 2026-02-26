package com.walker.dscommerce.service;

import com.walker.dscommerce.model.dto.CategoryDTO;
import com.walker.dscommerce.model.dto.ProductDTO;
import com.walker.dscommerce.model.dto.ProductMinDTO;
import com.walker.dscommerce.exception.DataBaseIntegrityViolationException;
import com.walker.dscommerce.exception.ResourceNotFoundException;
import com.walker.dscommerce.model.Category;
import com.walker.dscommerce.model.Product;
import com.walker.dscommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {
    //Dependências:
    private final ProductRepository productRepository; // Injeção do nosso repository
    private final ModelMapper modelMapper; // Injeção do nosso mapper


    //Construtor para injeção de dependência
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product não encontrado com ID: " + id)); //Tratando e lançando a exceção customizada
        return modelMapper.map(product, ProductDTO.class);
    }


    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> productPage = productRepository.searchByName(name, pageable);
        return productPage.map(product -> modelMapper.map(product, ProductMinDTO.class));
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDTOToEntity(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            copyDTOToEntity(productDTO, product);
            product = productRepository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product não encontrado com ID: " + id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataBaseIntegrityViolationException("Falha de integridade referencial!");
        }
    }



    private void copyDTOToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        product.getCategories().clear();
        for (CategoryDTO categoryDTO : productDTO.getCategories()) {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            product.getCategories().add(category);
        }
    }
}
