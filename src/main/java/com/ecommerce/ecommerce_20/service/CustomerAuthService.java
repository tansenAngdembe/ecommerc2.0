package com.ecommerce.ecommerce_20.service;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.customer.LoginRequest;

import java.security.Principal;

public interface CustomerAuthService {
     ApiResponse<?> login(LoginRequest loginRequest);
     ApiResponse<?> profile(Principal principal);
}
