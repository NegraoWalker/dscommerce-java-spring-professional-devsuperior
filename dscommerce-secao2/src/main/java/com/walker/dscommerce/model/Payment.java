package com.walker.dscommerce.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Definindo o Instant como UTC e não com o fuso horário local.
    private Instant moment;

    //Relacionamento 1X1 com Order: A classe Order é a que manda no relacionamento, isso porque não se pode ter Payment sem Order.
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;




    public Payment() {
    }
}
