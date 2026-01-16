package com.walker.dscommerce.model.dto;

import com.walker.dscommerce.model.Category;
import com.walker.dscommerce.model.Product;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO para transferência de dados do Product.
 * Contém apenas os campos essenciais que devem ser expostos via API.
 */
public class ProductDTO {
    //Fields:
    private Long id;
    @Size(min = 3, max = 80, message = "O campo name precisa ter de 3 a 80 caracteres!")
    @NotBlank(message = "O campo name é obrigatório!")
    private String name;
    @Size(min = 10, message = "O campo description precisa ter de no mínimo 10 caracteres!")
    @NotBlank(message = "O campo description é obrigatório!")
    private String description;
    @NotNull(message = "Campo price é obrigatório!")
    @Positive(message = "O campo price deve ser positivo!")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "O campo categories deve ter pelo menos uma categoria!")
    private List<CategoryDTO> categories = new ArrayList<>();

    //Constructors:
    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, List<CategoryDTO> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories;
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        for (Category category : product.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }


    //Getters and Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
