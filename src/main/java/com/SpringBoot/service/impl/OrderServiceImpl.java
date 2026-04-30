package com.SpringBoot.service.impl;

import com.SpringBoot.domain.OrderStatus;
import com.SpringBoot.domain.PaymentStatus;
import com.SpringBoot.model.*;
import com.SpringBoot.repository.AddressRepository;
import com.SpringBoot.repository.OrderItemRepository;
import com.SpringBoot.repository.OrderRepository;
import com.SpringBoot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {

        if(user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }
        Address address =addressRepository.save(shippingAddress);

        Map<Long ,List<CartItem>> itemsBySeller =cart.getCartItemSet().stream().collect(Collectors.groupingBy(item->item.getProduct()
                .getSeller().getId()));

        Set<Order> orders = new HashSet<>();

        for(Map.Entry<Long,List<CartItem>> entry:itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();

            int totalOrderPrices = items.stream().mapToInt(
                    CartItem::getSellingPrice
            ).sum();
            int totalItems=items.stream().mapToInt(CartItem::getQuantity).sum();

            Order createOrder = new Order();
            createOrder.setUser(user);
            createOrder.setSellerId(sellerId);
            createOrder.setTotalMrpPrice(totalOrderPrices);
            createOrder.setTotalItem(totalItems);
            createOrder.setShippingAddress(address);
            createOrder.setOrderStatus(OrderStatus.PENDING);
            createOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder=orderRepository.save(createOrder);
            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();

            for(CartItem item :items){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                savedOrder.getOrderItemList().add(orderItem);

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);

            }
        }

        return orders;
    }

    @Override
    public Order findOrderById(long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(()->
                new Exception("order not found"));
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellerOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {

        Order order =findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order CancelOrder(Long orderId, User user) throws Exception {

        Order order =findOrderById(orderId);

        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("you don't have access to this order");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);


    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id).orElseThrow(()->
                new Exception("order item not exist...."));
    }


}
