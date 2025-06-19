package com.ecommerce.ecommerce_20.dto.Response.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
