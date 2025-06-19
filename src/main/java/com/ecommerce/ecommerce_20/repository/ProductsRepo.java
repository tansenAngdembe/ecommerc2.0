package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepo extends JpaRepository<Products, Long> {

     Optional<Products>findByUniqueId(String uniqueId);
}
