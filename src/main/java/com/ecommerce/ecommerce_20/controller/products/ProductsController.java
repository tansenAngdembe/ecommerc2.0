package com.ecommerce.ecommerce_20.controller.products;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.ProductByIdRequest;
import com.ecommerce.ecommerce_20.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {
    private final ProductService productService;
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/lists")
    public ApiResponse<?> listAllProducts() {
        return productService.getAllProudcts();
    }

    @PostMapping("/view")
    public ApiResponse<?> productView(@RequestBody ProductByIdRequest productByIdRequest) {
        return productService.getProductById(productByIdRequest);
    }



}
