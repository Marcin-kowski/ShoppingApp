package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.ProductEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByNameIgnoreCase(String name);


}
