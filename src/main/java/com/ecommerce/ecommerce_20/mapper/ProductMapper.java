package com.ecommerce.ecommerce_20.mapper;

import com.ecommerce.ecommerce_20.dto.Response.product.ProductResponse;
import com.ecommerce.ecommerce_20.entity.product.Products;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {

    public abstract ProductResponse getProduct(Products products);

    public List<ProductResponse> getProductsList(List<Products> productsList) {
        return productsList.stream().map(this::getProduct).collect(Collectors.toList());
    }


}
