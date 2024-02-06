package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.dto.ProductBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.exception.ProductNotFoundException;
import com.mrcinkowski.ShoppingApp.repository.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProduct(long productId) throws ProductNotFoundException;
    void deleteProduct(long productId);
    ProductEntity addProduct(ProductBody productBody) throws ProductAlreadyExistsException;

}
