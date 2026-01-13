package com.walker.dscommerce.repository;

import com.walker.dscommerce.model.OrderItem;
import com.walker.dscommerce.model.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
