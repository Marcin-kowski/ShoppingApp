package com.mrcinkowski.ShoppingApp.service.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String country;

    private String zipCode;
}
