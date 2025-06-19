package com.ecommerce.ecommerce_20.entity.customer;

import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.entity.product.Products;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItems> cartItems = new ArrayList<>();


}
