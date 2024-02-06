package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.AddressEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;


public interface AddressRepository extends ListCrudRepository<AddressEntity, Long> {
    List<AddressEntity> findByUser(LocalUserEntity user);

    Optional<AddressEntity> findByUserId(Long id);




}
