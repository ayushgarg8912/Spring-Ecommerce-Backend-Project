package com.SpringBoot.Controller;

import com.SpringBoot.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Welcome to ecommerce multivendor system");
   return  apiResponse;
    }
}
