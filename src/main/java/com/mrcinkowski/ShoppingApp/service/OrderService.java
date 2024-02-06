package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.ShopOrderEntity;

import java.util.List;

public interface OrderService {
    List<ShopOrderEntity> getOrders(LocalUserEntity user);
}
