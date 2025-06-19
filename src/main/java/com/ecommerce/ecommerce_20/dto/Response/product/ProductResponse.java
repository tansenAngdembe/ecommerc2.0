package com.ecommerce.ecommerce_20.dto.Response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private String uniqueId;
    private String productName;
    private Double price;
    private Long quantity;
    private String images;
    private String shortDescription;
    private String longDescription;
    private Boolean isAvilable;
    private CategoriesResponse category;
    private Long rating;

}
