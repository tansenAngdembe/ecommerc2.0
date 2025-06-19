package com.ecommerce.ecommerce_20.dto.Response.profile;
import com.ecommerce.ecommerce_20.entity.customer.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String profilePictureUrl;
    private Instant createdAt;

    private List<AddressResponse> addresses;

}
