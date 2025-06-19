package com.ecommerce.ecommerce_20.dto.Request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class PaymentProcessRequest {
    private Long orderId;
}
