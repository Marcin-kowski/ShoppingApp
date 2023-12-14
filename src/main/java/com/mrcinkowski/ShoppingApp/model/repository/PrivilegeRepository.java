package com.mrcinkowski.ShoppingApp.model.repository;

import com.mrcinkowski.ShoppingApp.model.Privilege;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;


public interface PrivilegeRepository extends ListCrudRepository<Privilege, Long> {
    Optional<Privilege> findByName(String name);

}
