package com.ecommerce.ecommerce_20.controller.customer;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.AddCartItemsRequest;
import com.ecommerce.ecommerce_20.dto.Request.CartRequest;
import com.ecommerce.ecommerce_20.dto.Request.OrderRequest;
import com.ecommerce.ecommerce_20.service.CartService;
import com.ecommerce.ecommerce_20.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping("/addToCart")
    public ApiResponse<?> addToCart(@RequestBody AddCartItemsRequest cartItemsRequest, Principal principal) {
        return  cartService.addItemToCart(cartItemsRequest, principal);
    }
    @PostMapping("/myCart")
    public ApiResponse<?> myCart(Principal principal) {
        return  cartService.getCartItems(principal);
    }

    @PostMapping("/removeItems")
    public ApiResponse<?> removeItems(@RequestBody CartRequest cartRequest, Principal principal){
        return cartService.removeItemFromCart(cartRequest, principal);
    }

    @PostMapping("/placeOrder")
    public ApiResponse<?> placeOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
            return orderService.placeOrder(orderRequest, principal);
    }
}
 