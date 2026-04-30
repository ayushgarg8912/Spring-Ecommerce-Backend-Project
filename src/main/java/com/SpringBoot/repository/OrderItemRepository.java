package com.SpringBoot.repository;

import com.SpringBoot.model.Order;
import com.SpringBoot.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
