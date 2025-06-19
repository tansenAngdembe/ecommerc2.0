package com.ecommerce.ecommerce_20.service.impl;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.customer.AddressRequest;
import com.ecommerce.ecommerce_20.dto.Request.customer.RegisterRequest;
import com.ecommerce.ecommerce_20.dto.Response.profile.AddressResponse;
import com.ecommerce.ecommerce_20.entity.customer.Address;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.mapper.CustomersMapper;
import com.ecommerce.ecommerce_20.repository.AddressRepo;
import com.ecommerce.ecommerce_20.repository.CustomersRepo;
import com.ecommerce.ecommerce_20.service.CustomerService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomersRepo customersRepo;
    private final AddressRepo addressRepo;
    private final CustomersMapper customersMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        try {
            if (customersRepo.findByEmail(registerRequest.getEmail()).isPresent()) {
                return ResponseUtil.getSuccessMessage(-1, "User already exist this email.");
            }

            if (customersRepo.findByMobileNumber(registerRequest.getMobileNumber()).isPresent()) {
                return ResponseUtil.getSuccessMessage(-1, "Mobile number already exist.");
            }

            Customers registerCustomer = customersMapper.registerCustomer(registerRequest);
            registerCustomer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            registerCustomer.setCreatedAt(Instant.now());
            registerCustomer.setWrongPasswordAttemptCount(0);
            registerCustomer.setIsAccountLocked(false);

            Customers isSaved = customersRepo.save(registerCustomer);
            if (isSaved != null) {
                return ResponseUtil.getSuccessMessage(0, "Customer registered successfully.");

            }
            return ResponseUtil.getSuccessMessage(-1, "Customer registered Failed.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.getFailureMessage(-1, "Internal Server Error");
        }

    }


    @Override
    public ApiResponse<?> address(AddressRequest addressRequest, Principal principal) {
        try {
            Customers customer = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));

            Address address = customersMapper.addAddress(addressRequest);
            address.setCustomer(customer);
            addressRepo.save(address);
            return ResponseUtil.getSuccessMessage(0, "Address set successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.getFailureMessage(-1, "Internal Server Error");
        }

    }

    @Override
    public ApiResponse<?> getAddress( Principal principal) {
        Customers customer = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Address> addresses = customer.getAddresses();
        List<AddressResponse> addressResponses = customersMapper.mapToAddressResponse(addresses);

        return ResponseUtil.getSuccessMessage(1,"Address fetched successfully.",addressResponses);
    }

}
