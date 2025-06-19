package com.ecommerce.ecommerce_20.dto.Response.cart;

import com.ecommerce.ecommerce_20.dto.Response.product.ProductListResponse;
import com.ecommerce.ecommerce_20.dto.Response.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CartItemsListResponse {
   private CartResponse product;
   private Long quantity;

}
