package com.SpringBoot.model;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Data

public class BankDetails {

    private String accountNumber;

    private String accountHolderName;

    private String ifscCode;



}
