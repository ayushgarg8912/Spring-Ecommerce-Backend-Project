package com.SpringBoot.request;

import com.SpringBoot.domain.USER_ROLE;
import com.SpringBoot.model.User;
import lombok.Data;

@Data
public class LoginOtpRequest {
   private String email;
   private String otp;

   private USER_ROLE role;

}
