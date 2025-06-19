package com.ecommerce.ecommerce_20.repository;

import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepo extends JpaRepository<Customers, Long> {
   Optional<Customers> findByEmail(String email);

   Optional<Object> findByMobileNumber(@NotBlank(message = "Mobile number is required.") String mobileNumber);
}
