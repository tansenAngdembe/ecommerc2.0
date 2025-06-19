package com.ecommerce.ecommerce_20.service.impl;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.ProductByIdRequest;
import com.ecommerce.ecommerce_20.dto.Response.product.ProductListResponse;
import com.ecommerce.ecommerce_20.dto.Response.product.ProductResponse;
import com.ecommerce.ecommerce_20.entity.product.Products;
import com.ecommerce.ecommerce_20.mapper.ProductMapper;
import com.ecommerce.ecommerce_20.repository.ProductsRepo;
import com.ecommerce.ecommerce_20.service.ProductService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public  class ProductServiceImpl implements ProductService {

    private final ProductsRepo productsRepo;
    private final  ProductMapper productMapper;


    @Override
    public ApiResponse<?> getAllProudcts() {
        try {
            List<Products> allProdcuts = productsRepo.findAll();

            List<ProductResponse> allResponse = productMapper.getProductsList(allProdcuts);

            ProductListResponse allListResponser = new ProductListResponse();
            allListResponser.setTotal(allProdcuts.stream().count());
            allListResponser.setProducts(allResponse);

            log.info(" EetAllProudcts success");
            return ResponseUtil.getSuccessMessage(0, "Product fetch successfully.",  allListResponser);

        } catch(Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public ApiResponse<?> getProductById(ProductByIdRequest request){
        try{
            Products product = productsRepo.findByUniqueId(request.getUniqueId()).orElseThrow(()-> new RuntimeException("Product not found."));
            ProductResponse productResponse = productMapper.getProduct(product);
            return ResponseUtil.getSuccessMessage(0, "Product fetch successfully.",  productResponse);

        }catch(Exception e){
            e.printStackTrace();
            return null;

        }

    }
}
