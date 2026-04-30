package com.SpringBoot.repository;

import com.SpringBoot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userid);
    List<Order> findBySellerId(Long sellerId);


}
