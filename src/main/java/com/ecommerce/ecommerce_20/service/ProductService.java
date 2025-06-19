package com.ecommerce.ecommerce_20.service;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.ProductByIdRequest;

public interface ProductService {
    ApiResponse<?> getAllProudcts();
    ApiResponse<?> getProductById(ProductByIdRequest request);
}
