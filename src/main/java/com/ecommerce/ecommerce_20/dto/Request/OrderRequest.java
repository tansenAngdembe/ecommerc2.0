package com.ecommerce.ecommerce_20.dto.Request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long addressId;
    List<String> productIds;

}
