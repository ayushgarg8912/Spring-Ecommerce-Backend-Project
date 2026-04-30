package com.SpringBoot.service;


import com.SpringBoot.domain.OrderStatus;
import com.SpringBoot.model.*;


import java.util.List;
import java.util.Set;

public interface OrderService {

    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
    Order findOrderById(long id) throws Exception;
    List<Order> userOrderHistory(Long userId);
    List<Order> sellerOrder(Long sellerId);
    Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;
    Order CancelOrder(Long orderId,User user) throws Exception;
    OrderItem getOrderItemById(Long id) throws Exception;



}
