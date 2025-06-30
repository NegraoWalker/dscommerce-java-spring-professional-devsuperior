package com.walker.dscommerce.dto;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgURL;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, String imgURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgURL() {
        return imgURL;
    }
}
