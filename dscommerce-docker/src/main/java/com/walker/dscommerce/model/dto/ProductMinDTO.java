package com.walker.dscommerce.model.dto;


/**
 * DTO para transferência de dados do Product.
 * Contém apenas os campos essenciais que devem ser expostos via API.
 */
public class ProductMinDTO {
    //Fields:
    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    //Constructors:
    public ProductMinDTO() {
    }

    public ProductMinDTO(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
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
}
