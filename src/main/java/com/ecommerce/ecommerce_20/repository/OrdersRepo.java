package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.Orders;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    }
