package com.mrcinkowski.ShoppingApp.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private InventoryEntity inventoryEntity;

}