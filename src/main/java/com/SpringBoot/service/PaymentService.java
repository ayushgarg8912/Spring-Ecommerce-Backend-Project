package com.SpringBoot.service;

import com.SpringBoot.model.Order;
import com.SpringBoot.model.PaymentOrder;
import com.SpringBoot.model.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

import java.util.Set;

public interface PaymentService {

    PaymentOrder createOrder(User user, Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;

    PaymentLink createRazorpayPaymentLink(User user,Long amount,Long orderId) throws RazorpayException;



}
