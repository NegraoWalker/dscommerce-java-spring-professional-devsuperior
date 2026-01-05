package com.walker.dscommerce.service;

import com.walker.dscommerce.dto.OrderDTO;
import com.walker.dscommerce.exception.ResourceNotFoundException;
import com.walker.dscommerce.model.Order;
import com.walker.dscommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order não encontrado com ID: " + id));
        return new OrderDTO(order);
    }
}
