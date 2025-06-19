package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.product.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories, Long> {
}
