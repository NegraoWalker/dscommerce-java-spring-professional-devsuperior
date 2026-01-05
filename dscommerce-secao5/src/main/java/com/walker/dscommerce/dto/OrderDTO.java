package com.walker.dscommerce.dto;

import com.walker.dscommerce.enums.OrderStatus;
import com.walker.dscommerce.model.Order;
import com.walker.dscommerce.model.OrderItem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;

    private ClientDTO client;
    private PaymentDTO payment;
    private List<OrderItemDTO> items = new ArrayList<>();


    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, ClientDTO client, PaymentDTO payment, List<OrderItemDTO> items) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.payment = payment;
        this.items = items;
    }

    public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        orderStatus = order.getOrderStatus();
        client = new ClientDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem);
            items.add(orderItemDTO);
        }
    }

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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public Double getTotal() {
        double sum = 0;
        for (OrderItemDTO orderItemDTO : items) {
            sum = sum + orderItemDTO.getSubTotal();
        }
        return sum;
    }
}
