package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.OrdersItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemsRepo extends JpaRepository<OrdersItems, Long> {
}
