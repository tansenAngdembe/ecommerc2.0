package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
