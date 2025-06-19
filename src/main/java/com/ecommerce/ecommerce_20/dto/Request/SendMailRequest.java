package com.ecommerce.ecommerce_20.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    @NotBlank(message = "Recipient email required.")
    private String recipient;
    @NotBlank(message = "Subject is required.")
    private String subject;
    @NotBlank(message = "Message is required.")
    private String message;
}
