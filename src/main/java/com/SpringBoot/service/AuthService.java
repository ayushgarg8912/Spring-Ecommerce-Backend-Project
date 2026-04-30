package com.SpringBoot.service;

import com.SpringBoot.domain.USER_ROLE;
import com.SpringBoot.request.LoginRequest;
import com.SpringBoot.response.AuthResponse;
import com.SpringBoot.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email , USER_ROLE role) throws Exception;

    String createUser(SignupRequest req) throws Exception;

    AuthResponse signing(LoginRequest req) throws Exception;


}
