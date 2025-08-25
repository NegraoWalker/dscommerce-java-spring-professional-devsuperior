package com.walker.dscommerce.model;

import com.walker.dscommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    //Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Definindo o Instant como UTC e não com o fuso horário local
    private Instant moment;

    private OrderStatus orderStatus;

     /* REGRA PRÁTICA: RELACIONAMENTOS
        Em 1:N → O lado "N" sempre é proprietário
        Em 1:1 → Quem depende do outro é proprietário
        Em N:N → Quem você consulta mais frequentemente
    */

    //Relacionamento Nx1 com User: Order é o lado N então Order é o proprietário do relacionamento
    @ManyToOne
    @JoinColumn(name = "client_id") //A coluna de junção entre as tabelas será a foreign key chamada client_id. Ou seja, para cada order tá associado um user.
    private User client;

    //Relacionamento 1X1 com Order: Payment depende de Order então Payment é o proprietário do relacionamento
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    //
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> orderItems = new HashSet<>();

    //Constructors:
    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client, Payment payment) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.payment = payment;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    } //

    public List<Product> getProducts() {
        return orderItems.stream().map(OrderItem::getProduct).toList();
    } //

    //Equals and HashCode:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
