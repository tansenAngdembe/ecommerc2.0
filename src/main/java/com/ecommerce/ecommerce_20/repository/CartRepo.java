package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.Cart;
import com.ecommerce.ecommerce_20.entity.customer.CartItems;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomers(Customers loadUser);

    Cart findByCartItemsAndCartItemsIsDeletedFalse(Cart cart);

}
