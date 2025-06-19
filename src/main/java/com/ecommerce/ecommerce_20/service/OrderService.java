package com.ecommerce.ecommerce_20.service;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.OrderRequest;
import jakarta.persistence.criteria.Order;

import java.security.Principal;

public interface OrderService {
    ApiResponse<?> placeOrder(OrderRequest orderRequest, Principal principal);
}
