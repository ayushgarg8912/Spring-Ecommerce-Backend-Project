package com.SpringBoot.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Data

public class BusinessDetails {

    private String businessName;

    private String businessEmail;

    private String businessMobile;

    private String logo;

    private String banner;


}
