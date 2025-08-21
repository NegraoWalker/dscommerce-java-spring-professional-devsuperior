package com.walker.dscommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String password;

    //Roles

    //Relacionamento 1xN com Order: A classe User é a que manda no relacionamento, isso porque não se pode ter Order sem User.
    @OneToMany(mappedBy = "client") //Sempre a classe que manda vai ter o mappedBy.
    private List<Order> orders = new ArrayList<>();

    //Constructors:
    public User() {
    }

    //Getters and Setters:

    //Equals and Hashcode:

}
