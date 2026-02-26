package com.walker.dscommerce.model.dto;

import com.walker.dscommerce.model.User;

public class ClientDTO {
    private Long id;
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDTO(User user) {
        id = user.getId();
        name = user.getName();
    }

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
}
