package com.mrcinkowski.ShoppingApp.model.repository;

import com.mrcinkowski.ShoppingApp.model.Address;
import com.mrcinkowski.ShoppingApp.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends ListCrudRepository<Address, Long> {
    List<Address> findByUser(LocalUser user);

    Optional<Address> findByUserId(Long id);




}
