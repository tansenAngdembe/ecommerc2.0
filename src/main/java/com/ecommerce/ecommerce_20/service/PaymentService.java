package com.ecommerce.ecommerce_20.service;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.CodPaymentRequest;
import com.ecommerce.ecommerce_20.dto.Request.PaymentProcessRequest;
import com.ecommerce.ecommerce_20.entity.customer.Orders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.Map;

public interface PaymentService {

    ApiResponse<?> processPayment(PaymentProcessRequest paymentProcessRequest  );

    ResponseEntity<Void> verifyPayment(String orderId,Map<String,String> params);

    ApiResponse<?> processCodPayment(PaymentProcessRequest  paymentProcessRequest  );
}
