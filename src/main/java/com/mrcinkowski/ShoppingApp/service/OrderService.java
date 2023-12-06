package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.ShopOrder;
import com.mrcinkowski.ShoppingApp.model.repository.ShopOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private ShopOrderRepository shopOrderRepository;

    public OrderService(ShopOrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public List<ShopOrder> getOrders(LocalUser user) {
        return shopOrderRepository.findByUser(user);
    }
}
