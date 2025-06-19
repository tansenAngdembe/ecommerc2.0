package com.ecommerce.ecommerce_20.dto.Request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    @NotBlank(message = "Street should not be blank.")
    private String street;
    @NotBlank(message = "City should not be blank.")
    private String city;
    @NotBlank(message = "State should not be blank.")
    private String state;
    @NotBlank(message = "Zip code should not be blank.")
    private String zip;
    @NotBlank(message = "Phone should not be blank.")
    @Size(min=10,max=10,message = "Phone should only contain 10 digits")
    private String phone;
}
