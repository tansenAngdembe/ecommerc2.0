package com.ecommerce.ecommerce_20.entity.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name" ,nullable = false, length = 50)
    private String productName;

    @Column(name="price", nullable=false)
    private Double price;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="images_url")
    private String images;

    @Column(name="short_description")
    private String shortDescription;

    @Column(name="long_descriptiton")
    private String longDescription;

    @Column(name="unique_id",nullable = false,updatable = false)
    private String uniqueId;

    @Column(name ="created_at",updatable = false)
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name="is_avilable", nullable = false)
    private Boolean isAvilable;

    @Column(name="rating")
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Categories category;


    @PrePersist
    protected void  generateUUID(){
        this.uniqueId = UUID.randomUUID().toString();
    }
}
