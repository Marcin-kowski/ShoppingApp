package com.mrcinkowski.ShoppingApp.api.controller.address.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDto {

    private String city;

    private String country;

    private String zipCode;
}
