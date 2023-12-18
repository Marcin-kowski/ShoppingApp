package com.mrcinkowski.ShoppingApp.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddressBody {
    @NotNull
    @NotBlank
    private String addressLine1;
    @NotNull
    private String addressLine2;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String zipCode;
}
