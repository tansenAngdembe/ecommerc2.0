package com.ecommerce.ecommerce_20.service;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.customer.AddressRequest;
import com.ecommerce.ecommerce_20.dto.Request.customer.RegisterRequest;
import com.ecommerce.ecommerce_20.dto.Response.profile.AddressResponse;

import java.security.Principal;

public interface CustomerService {
  ApiResponse<?> register(RegisterRequest registerRequest);
  ApiResponse<?> address(AddressRequest addressRequest, Principal principal);
  ApiResponse<?> getAddress( Principal principal);


}
