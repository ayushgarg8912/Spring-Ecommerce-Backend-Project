package com.SpringBoot.Controller;

import com.SpringBoot.domain.USER_ROLE;
import com.SpringBoot.model.User;
import com.SpringBoot.model.VerificationCode;
import com.SpringBoot.repository.UserRepository;
import com.SpringBoot.request.LoginOtpRequest;
import com.SpringBoot.request.LoginRequest;
import com.SpringBoot.response.ApiResponse;
import com.SpringBoot.response.AuthResponse;
import com.SpringBoot.response.SignupRequest;

import com.SpringBoot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {


        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register succcess");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);


        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sendotpHandler(@RequestBody LoginOtpRequest req) throws Exception {


        authService.sentLoginOtp(req.getEmail(),req.getRole());

        ApiResponse res = new ApiResponse();

        res.setMessage("otp sent succcessfully");


        return ResponseEntity.ok(res);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {


        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);
}
}
