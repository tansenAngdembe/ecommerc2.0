package com.ecommerce.ecommerce_20.controller.customer.auth;


import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.customer.LoginRequest;
import com.ecommerce.ecommerce_20.service.CustomerAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final CustomerAuthService customerAuthService;
    public AuthController(CustomerAuthService customerAuthService) {
        this.customerAuthService = customerAuthService;
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest loginRequest) {
        return customerAuthService.login(loginRequest);
    }
    @PostMapping("/profile")
    public ApiResponse<?> profile(Principal principal) {
        return customerAuthService.profile(principal);
    }
}
