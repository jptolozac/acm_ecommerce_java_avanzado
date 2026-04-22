package com.acm.ecommerce.entities;


import jakarta.persistence.Entity;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")


public class ProductEntity {

}
