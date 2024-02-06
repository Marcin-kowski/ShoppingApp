package com.mrcinkowski.ShoppingApp.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ShopOrderQuantitiesEntity> quantities = new ArrayList<>();

}