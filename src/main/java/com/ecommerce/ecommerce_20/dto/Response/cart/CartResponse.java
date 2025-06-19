package com.ecommerce.ecommerce_20.dto.Response.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private String uniqueId;
    private String productName;
    private Long price;
    private String images;
    private String shortDescription;

}