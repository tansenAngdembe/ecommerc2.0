package com.ecommerce.ecommerce_20.mapper;


import com.ecommerce.ecommerce_20.dto.Request.customer.AddressRequest;
import com.ecommerce.ecommerce_20.dto.Request.customer.RegisterRequest;
import com.ecommerce.ecommerce_20.dto.Response.profile.AddressResponse;
import com.ecommerce.ecommerce_20.dto.Response.profile.ProfileResponse;
import com.ecommerce.ecommerce_20.entity.customer.Address;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CustomersMapper {
    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "wrongPasswordAttemptCount", ignore = true),
            @Mapping(target = "isAccountLocked", ignore = true),
    })
    public abstract Customers registerCustomer(RegisterRequest registerRequest);

    @Mapping(target = "customer", ignore = true) // handled manually
    public abstract Address addAddress(AddressRequest addressRequest);


    // customer profile
    public abstract ProfileResponse customerToProfile(Customers customers);

    public abstract AddressResponse addressResponse(Address address);

    public List<AddressResponse> mapToAddressResponse(List<Address> addresses) {
       return addresses.stream().map(this::addressResponse).collect(Collectors.toList());

    }

}
