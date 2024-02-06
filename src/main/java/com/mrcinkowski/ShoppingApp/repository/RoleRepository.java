package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.RoleEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface RoleRepository extends ListCrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

}
