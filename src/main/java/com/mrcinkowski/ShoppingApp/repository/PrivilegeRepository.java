package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.PrivilegeEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;


public interface PrivilegeRepository extends ListCrudRepository<PrivilegeEntity, Long> {
    Optional<PrivilegeEntity> findByName(String name);

}
