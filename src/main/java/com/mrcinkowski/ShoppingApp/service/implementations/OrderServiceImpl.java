package com.mrcinkowski.ShoppingApp.service.implementations;

import com.mrcinkowski.ShoppingApp.repository.ShopOrderRepository;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.ShopOrderEntity;
import com.mrcinkowski.ShoppingApp.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final ShopOrderRepository shopOrderRepository;

    public OrderServiceImpl(ShopOrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public List<ShopOrderEntity> getOrders(LocalUserEntity user) {
        return shopOrderRepository.findByUser(user);
    }
}
