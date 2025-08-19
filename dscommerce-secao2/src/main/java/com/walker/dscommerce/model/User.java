package com.walker.dscommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    //fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String password;

    //roles

    //constructors:
    public User() {
    }

    //getters and setters:

    //equals and hashcode:

}
