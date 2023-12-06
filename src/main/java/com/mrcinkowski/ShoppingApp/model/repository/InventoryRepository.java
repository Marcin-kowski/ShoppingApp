package com.mrcinkowski.ShoppingApp.model.repository;

import com.mrcinkowski.ShoppingApp.model.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
