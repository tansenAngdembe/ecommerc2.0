package com.ecommerce.ecommerce_20.entity.customer;


import com.ecommerce.ecommerce_20.entity.product.Products;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "orders_items")
public class OrdersItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "order_id", referencedColumnName = "id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name ="product_id", referencedColumnName = "id")
    private Products products;

    private Double price;
    private Long quantity;





}
