package com.SpringBoot.Controller;


import com.SpringBoot.domain.PaymentMethod;
import com.SpringBoot.model.*;
import com.SpringBoot.repository.PaymentOrderRepository;
import com.SpringBoot.response.PaymentLinkResponse;
import com.SpringBoot.service.*;
import com.razorpay.PaymentLink;
import jakarta.mail.search.SearchTerm;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAAddress ,
            @RequestParam PaymentMethod paymentMethod ,
            @RequestHeader("Authorization") String jwt)
        throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Cart cart= cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user,shippingAAddress,cart);

        PaymentOrder paymentOrder = paymentService.createOrder(user,orders);

        PaymentLinkResponse res = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.RAZORPPAY)){
            PaymentLink payment = paymentService.createRazorpayPaymentLink(user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            String paymentUrl =payment.get("short_url");
            String paymentUrlId=payment.get("id");

            res.setPayment_Link_url(paymentUrl);

            paymentOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(paymentOrder);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(
            @RequestHeader("Authoriztion")
            String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.userOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId ,
                                              @RequestHeader("Authorization")
                                              String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Order orders = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);


    }

    @GetMapping("/item/{ordersItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long orderItemId,@RequestHeader("Authorization") String jwt ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem=orderService.getOrderItemById(orderItemId);
        return  new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
    }
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.CancelOrder(orderId, user);

        Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport report = sellerReportService.getSellerReport(seller);

        report.setCancelOrders(report.getCancelOrders() + 1);
        report.setTotalRefunds(report.getTotalRefunds() + order.getTotalSellerPrice());

        sellerReportService.updateSellerReport(report);

        return ResponseEntity.ok(order);
    }


}
