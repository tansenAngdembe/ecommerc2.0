package com.ecommerce.ecommerce_20.entity.customer;

import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "payment_transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_code", nullable = false,unique = true)
    private  String transactionCode;
    @Column(name = "transaction_uuid", nullable = false, unique = true)
    private  String transactionUuid;
    @Column(name="status", nullable = false)
    private  String status;
    @Column(name="transaction_signature" ,nullable = false,unique = true)
    private  String transactionSignature;
    @Column(name="transaction_type",nullable = false)
    private  String transactionType;
    @Column(name="transaction_total_amount")
    private  String transactionTotalAmount;

    @Column(name = "transaction_product_code")
    private  String transactionProductCode;

    @Column(name="transaction_date")
    private Instant transactionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

}
