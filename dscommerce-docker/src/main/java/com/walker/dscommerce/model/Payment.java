package com.walker.dscommerce.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Definindo o Instant como UTC e não com o fuso horário local.
    private Instant moment;

    /* REGRA PRÁTICA: RELACIONAMENTOS
       Em 1:N → O lado "N" sempre é proprietário
       Em 1:1 → Quem depende do outro é proprietário
       Em N:N → Quem você consulta mais frequentemente
   */

    //Relacionamento 1X1 com Order: Payment depende de Order então Payment é o proprietário do relacionamento
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Constructors:
    public Payment() {
    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    //Getters and Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //Equals and HashCode:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
