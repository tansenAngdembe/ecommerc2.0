package com.ecommerce.ecommerce_20.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartItemsRequest {

        private String productId;
        private Long quantity;
}
