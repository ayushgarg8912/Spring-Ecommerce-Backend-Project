package com.SpringBoot.model;


import com.SpringBoot.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {

    private String paymentId;

    private String rozarpayPaymentLinkId;

    private String rozarpayPaymentLinkReferenceId;

    private String rozarpayPaymentLinkStatus;

    private String rozarpayPaymentIdZWSP;

    private PaymentStatus status;







}
