package com.mrcinkowski.ShoppingApp.api.controller.order;

import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.ShopOrderEntity;
import com.mrcinkowski.ShoppingApp.service.implementations.OrderServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<ShopOrderEntity> getOrders(@AuthenticationPrincipal LocalUserEntity user) {
        return orderService.getOrders(user);
    }
}


