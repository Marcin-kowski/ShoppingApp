package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.ShopOrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ShopOrderRepository extends ListCrudRepository<ShopOrderEntity, Long> {
    List<ShopOrderEntity> findByUser(LocalUserEntity user);
}
