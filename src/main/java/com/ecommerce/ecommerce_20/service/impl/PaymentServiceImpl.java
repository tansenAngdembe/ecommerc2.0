package com.ecommerce.ecommerce_20.service.impl;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.CodPaymentRequest;
import com.ecommerce.ecommerce_20.dto.Request.PaymentProcessRequest;
import com.ecommerce.ecommerce_20.dto.Response.EsewaResponse;
import com.ecommerce.ecommerce_20.entity.customer.Orders;
import com.ecommerce.ecommerce_20.entity.customer.PaymentTransaction;
import com.ecommerce.ecommerce_20.enums.PaymentMethod;
import com.ecommerce.ecommerce_20.enums.PaymentStatus;
import com.ecommerce.ecommerce_20.repository.OrdersRepo;
import com.ecommerce.ecommerce_20.repository.PaymentTransactionRepo;
import com.ecommerce.ecommerce_20.service.PaymentService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import com.ecommerce.ecommerce_20.util.constant.Constants;
import com.ecommerce.ecommerce_20.util.constant.EsewaConstant;
import com.ecommerce.ecommerce_20.util.service.EsewaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final EsewaService esewaService;

    private final PaymentTransactionRepo paymentTransactionRepo;
    private final OrdersRepo ordersRepo;

    public PaymentServiceImpl(EsewaService esewaService, PaymentTransactionRepo paymentTransactionRepo, OrdersRepo ordersRepo) {
        this.esewaService = esewaService;
        this.paymentTransactionRepo = paymentTransactionRepo;
        this.ordersRepo = ordersRepo;
    }


    @Override
    public ApiResponse<?> processCodPayment(PaymentProcessRequest paymentProcessRequest) {
        Optional<Orders> orderDb = ordersRepo.findById(paymentProcessRequest.getOrderId());
        Orders order = orderDb.get();
        if(order.getPaymentStatus().equals(PaymentStatus.COMPLETED)){
            return ResponseUtil.getSuccessMessage(0,"Payment is already completed");
        }
        order.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
        ordersRepo.save(order);
        return ResponseUtil.getSuccessMessage(1, "Order confirmed! You will pay on delivery.");
    }


    @Override
    public ApiResponse<?> processPayment(PaymentProcessRequest paymentProcessRequest) {

        Optional<Orders> orderDb = ordersRepo.findById(paymentProcessRequest.getOrderId());
        if (orderDb.isEmpty()) {
            return ResponseUtil.getFailureMessage(0, "Order not found.");
        }

        Orders orders = orderDb.get();
        String transactionId = UUID.randomUUID().toString();
        String signature = esewaService.createSignature(orders.getTotalPrice(), transactionId, EsewaConstant.PRODUCTCODE);
        EsewaResponse esewaResponse = new EsewaResponse();
        esewaResponse.setAmount(orders.getTotalPrice());
        esewaResponse.setTransactionId(transactionId);
        esewaResponse.setProductCode(EsewaConstant.PRODUCTCODE);
        esewaResponse.setTotalAmount(orders.getTotalPrice());
        esewaResponse.setSuccessUrl(EsewaConstant.SUCCESSURL + orders.getId());
        esewaResponse.setFailureUrl(EsewaConstant.FAILURL);
        esewaResponse.setSignature(signature);
        return ResponseUtil.getSuccessMessage(1, "Esewa response.", esewaResponse);


    }

    @Override
    public ResponseEntity<Void> verifyPayment(String orderId, Map<String, String> params) {
        try {
            Long orderIdLong = Long.parseLong(orderId);
            String data = params.get("data");
            byte[] decoded = Base64.getDecoder().decode(data);
            String decodedData = new String(decoded, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode value = objectMapper.readTree(decodedData);
            System.out.println("Decoded data: " + decodedData);

            String transactionCode = value.get("transaction_code").asText();
            String status = value.get("status").asText();
            String signature = value.get("signature").asText();
            String totalAmount = value.get("total_amount").asText();
            String productCode = value.get("product_code").asText();
            String transactionUuid = value.get("transaction_uuid").asText();
            String signedFieldNames = value.get("signed_field_names").asText();

            String redirectUrl;

            String newSignature = esewaService.createResponseSignature(
                    transactionCode, status, totalAmount, transactionUuid, productCode, signedFieldNames
            );

            if (signature.equals(newSignature)) {
                switch (status) {
                    case Constants.COMPLETE:
                        Optional<Orders> ordersDb = ordersRepo.findById(orderIdLong);
                        Orders order = ordersDb.get();
                        PaymentTransaction paymentTransaction = new PaymentTransaction();
                        paymentTransaction.setTransactionCode(transactionCode);
                        paymentTransaction.setTransactionUuid(transactionUuid);
                        paymentTransaction.setStatus(status);
                        paymentTransaction.setTransactionSignature(newSignature);
                        paymentTransaction.setTransactionType(productCode);
                        paymentTransaction.setTransactionTotalAmount(totalAmount);
                        paymentTransaction.setTransactionProductCode(productCode);
                        paymentTransaction.setTransactionDate(Instant.now());
                        paymentTransaction.setOrder(order);
                        order.setPaymentMethod(PaymentMethod.ESEWA);
                        order.setPaymentStatus(PaymentStatus.COMPLETED);
                        ordersRepo.save(order);
                        paymentTransactionRepo.save(paymentTransaction);

                        redirectUrl = "http://localhost:5173/success";
                        break;

                    case Constants.CANCELED:
                        redirectUrl = "http://localhost:5173/canceled";
                        break;

                    case Constants.FULL_REFUND:
                        redirectUrl = "http://localhost:5173/refunded";
                        break;

                    default:
                        redirectUrl = "http://localhost:5173";
                        break;
                }

                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", redirectUrl)
                        .build();

            } else {
                System.out.println("Invalid signature received. Possible tampering or mismatch.");
                redirectUrl = "http://localhost:5173/failed?reason=invalid_signature";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Location", redirectUrl).build();

            }


        } catch (Exception e) {
            e.printStackTrace();
            String redirectUrl = "http://localhost:5173/failed/server-error";
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
        }
    }


}
