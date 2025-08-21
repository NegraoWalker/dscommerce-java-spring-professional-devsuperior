package com.walker.dscommerce.model;

import com.walker.dscommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Definindo o Instant como UTC e não com o fuso horário local.
    private Instant moment;
    private OrderStatus orderStatus;

    //Relacionamento Nx1 com User: A classe User é a que manda no relacionamento, isso porque não se pode ter Order sem User.
    @ManyToOne
    @JoinColumn(name = "client_id") //A coluna de junção entre as tabelas será a foreign key chamada client_id. Ou seja, para cada order tá associado um user.
    private User client;
    //Relacionamento 1X1 com Order: A classe Order é a que manda no relacionamento, isso porque não se pode ter Payment sem Order.
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }
}
