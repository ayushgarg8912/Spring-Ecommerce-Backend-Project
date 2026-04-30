package com.SpringBoot.Controller;


import com.SpringBoot.model.*;
import com.SpringBoot.response.ApiResponse;
import com.SpringBoot.response.PaymentLinkResponse;
import com.SpringBoot.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;

    @GetMapping("/api/payment/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        PaymentLinkResponse paymentLinkResponse;

        PaymentOrder paymentOrder=paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        Boolean paymentSuccess = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId
        );

        if (paymentSuccess){
            for(Order order:paymentOrder.getOrders()){

               transactionService.createTransaction(order);

                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellerPrice());
                report.setTotalSales(report.getTotalSales()+order.getOrderItemList().size());
                sellerReportService.updateSellerReport(report);
            }
        }
              ApiResponse res = new ApiResponse();
        res.setMessage("Payment Sucessfull");

        return new ResponseEntity<>(res, HttpStatus.OK);

    }


}
