package com.ecommerce.ecommerce_20.mapper;

import com.ecommerce.ecommerce_20.dto.Response.cart.CartItemsListResponse;
import com.ecommerce.ecommerce_20.entity.customer.Cart;
import com.ecommerce.ecommerce_20.entity.customer.CartItems;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CartsMapper {

    public abstract CartItemsListResponse getCartResponse(CartItems cart);

    public List<CartItemsListResponse> getCartResponseList(List<CartItems> cartList) {
        return cartList.stream().map(this::getCartResponse).collect(Collectors.toList());
    }
}
