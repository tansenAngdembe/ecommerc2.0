package com.ecommerce.ecommerce_20.dto.Response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponse {
    private Long total;
    private List<ProductResponse> products;
}
