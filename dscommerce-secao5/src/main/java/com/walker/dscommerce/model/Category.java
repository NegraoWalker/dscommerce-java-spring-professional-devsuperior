package com.walker.dscommerce.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* REGRA PRÁTICA: RELACIONAMENTOS
       Em 1:N → O lado "N" sempre é proprietário
       Em 1:1 → Quem depende do outro é proprietário
       Em N:N → Quem você consulta mais frequentemente
   */

    //Relacionamento NXN com Product: Foi escolhido Product como proprietário: Mais comum: "Mostrar produto X com suas categorias"; E-commerce típico: usuário vê produto → categorias aparecem como tags
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    //Constructors:
    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }

    //Equals and HashCode:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
