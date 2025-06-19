package com.ecommerce.ecommerce_20.entity.customer;


import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="street", nullable=false, length=50)
    private String street;

    @Column(name="city", length=50)
    private String city;

    @Column(name = "state", length=50)
    private String state;

    @Column(name="zip", length=10)
    private String zip;

    @Column(name="phone", length=10, nullable=false)
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customers customer;
}
