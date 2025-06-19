package com.ecommerce.ecommerce_20.entity.customer;


import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.enums.OrderStatus;
import com.ecommerce.ecommerce_20.enums.PaymentStatus;
import com.ecommerce.ecommerce_20.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="order_data")
    private Instant orderDate;

    @Column(name="payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customers customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersItems> ordersItems = new ArrayList<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name="shipping_address_id",referencedColumnName = "id", nullable = false)
    private Address shippingAddress;

    @Column(name = "total_price")
    private Double totalPrice;
}
