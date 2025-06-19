package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.Cart;
import com.ecommerce.ecommerce_20.entity.customer.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepo extends JpaRepository<CartItems, Long> {
    List<CartItems> findByCartAndIsDeletedFalse(Cart cart);

}
