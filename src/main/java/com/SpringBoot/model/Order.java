package com.SpringBoot.model;


import com.SpringBoot.domain.OrderStatus;
import com.SpringBoot.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    @OneToOne
    private User user;

    private Long sellerId;

     @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();

     @ManyToOne
     private Address shippingAddress;

     @Embedded
     private PaymentDetails paymentDetails = new PaymentDetails();

     private double totalMrpPrice;

     private Integer totalSellerPrice;

     private Integer discount;

     private OrderStatus orderStatus;

     private int totalItem;

     private PaymentStatus paymentStatus = PaymentStatus.PENDING;


     private LocalDateTime orderDate = LocalDateTime.now();

     private LocalDateTime deliverDate = orderDate.plusDays(7);






}
