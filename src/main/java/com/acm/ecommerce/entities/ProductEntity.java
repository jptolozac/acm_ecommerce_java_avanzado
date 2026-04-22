package com.acm.ecommerce.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")


public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double price;
    private Integer stock;


    @Version
    private Long version;
    private boolean deleted = Boolean.FALSE;
}
