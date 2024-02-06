package com.mrcinkowski.ShoppingApp.repository;

import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends CrudRepository<LocalUserEntity, Long> {
    Optional<LocalUserEntity> findByUsernameIgnoreCase(String username);

    Optional<LocalUserEntity> findByEmailIgnoreCase(String email);

}
