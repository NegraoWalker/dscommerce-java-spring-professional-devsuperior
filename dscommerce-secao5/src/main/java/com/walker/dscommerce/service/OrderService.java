package com.walker.dscommerce.service;

import com.walker.dscommerce.dto.OrderDTO;
import com.walker.dscommerce.dto.OrderItemDTO;
import com.walker.dscommerce.enums.OrderStatus;
import com.walker.dscommerce.exception.ResourceNotFoundException;
import com.walker.dscommerce.model.Order;
import com.walker.dscommerce.model.OrderItem;
import com.walker.dscommerce.model.Product;
import com.walker.dscommerce.model.User;
import com.walker.dscommerce.repository.OrderItemRepository;
import com.walker.dscommerce.repository.OrderRepository;
import com.walker.dscommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order não encontrado com ID: " + id));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO orderItemDTO : orderDTO.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, orderItemDTO.getQuantity(), product.getPrice());
            order.getOrderItems().add(orderItem);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());
        return new OrderDTO(order);
    }
}
