package com.ecommerce.ecommerce_20.service;


import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.AddCartItemsRequest;
import com.ecommerce.ecommerce_20.dto.Request.CartRequest;

import java.security.Principal;

public interface CartService {
    ApiResponse<?> addItemToCart(AddCartItemsRequest cartItemsRequest, Principal principal);
    ApiResponse<?> getCartItems(Principal principal);
    ApiResponse<?> removeItemFromCart(CartRequest cartRequest, Principal principal);
//    ApiResponse<?> checkout();
}
