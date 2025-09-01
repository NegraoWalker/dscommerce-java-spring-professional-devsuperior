package com.walker.dscommerce.enums;

public enum OrderStatus {
    WAITING_PAYMENT(0),
    PAID(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    OrderStatus(int i) {

    }
}
