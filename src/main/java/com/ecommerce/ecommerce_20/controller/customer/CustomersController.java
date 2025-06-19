package com.ecommerce.ecommerce_20.controller.customer;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.customer.AddressRequest;
import com.ecommerce.ecommerce_20.dto.Request.customer.RegisterRequest;
import com.ecommerce.ecommerce_20.dto.Response.profile.AddressResponse;
import com.ecommerce.ecommerce_20.entity.customer.Address;
import com.ecommerce.ecommerce_20.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class CustomersController {
    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ApiResponse<?> registerCustomer(@RequestBody RegisterRequest registerRequest) {
        return customerService.register(registerRequest);

    }

    @PostMapping("/setAddress")
    public ApiResponse<?> registerAddress(@RequestBody AddressRequest addressRequest, Principal principal) {
        return customerService.address(addressRequest, principal);
    }
    @PostMapping("/getAddress")
    public ApiResponse<?> getAddress( Principal principal) {
        return customerService.getAddress( principal);
    }
}
