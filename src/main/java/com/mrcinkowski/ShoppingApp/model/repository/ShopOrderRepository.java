package com.mrcinkowski.ShoppingApp.model.repository;

import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.ShopOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ShopOrderRepository extends ListCrudRepository<ShopOrder, Long> {
    List<ShopOrder> findByUser(LocalUser user);
}
