package com.mrcinkowski.ShoppingApp.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String e) {
        super(e);
    }
}
