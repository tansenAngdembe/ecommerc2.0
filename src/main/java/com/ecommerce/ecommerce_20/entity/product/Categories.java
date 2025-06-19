package com.ecommerce.ecommerce_20.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="categories")
public class Categories {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name="category_name" ,nullable = false, length = 50)
        private String categoryName;
        @Column(name="description" ,nullable = false)
        private String description;



}
