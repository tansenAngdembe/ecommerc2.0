package com.ecommerce.ecommerce_20.entity.customer;

import com.ecommerce.ecommerce_20.entity.product.Products;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Table(name="cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id",nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Products product;

    @Column(name="quantity")
    private Long quantity;

    @Column(name ="is_deleted")
    private Boolean isDeleted=false;


}
