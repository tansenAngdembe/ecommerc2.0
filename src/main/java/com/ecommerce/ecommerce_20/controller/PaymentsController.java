package com.ecommerce.ecommerce_20.controller;


import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.CodPaymentRequest;
import com.ecommerce.ecommerce_20.dto.Request.PaymentProcessRequest;
import com.ecommerce.ecommerce_20.entity.customer.Orders;
import com.ecommerce.ecommerce_20.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentsController {
    private final PaymentService paymentService;
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/success/{orderId}")
    public ResponseEntity<Void> verify(
            @PathVariable String orderId,
            @RequestParam Map<String, String> params ) {
        return paymentService.verifyPayment(orderId, params);
    }
    @PostMapping("/process-pay")
    public ApiResponse<?> processPay(@RequestBody PaymentProcessRequest  paymentProcessRequest) {
        return paymentService.processPayment(paymentProcessRequest);
    }
    @PostMapping("/cashOnDelivery")
    public ApiResponse<?> processCodPayment(@RequestBody PaymentProcessRequest  paymentProcessRequest  ){
        return paymentService.processCodPayment(paymentProcessRequest);
    }
}
