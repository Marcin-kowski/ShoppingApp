package com.mrcinkowski.ShoppingApp.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductBody {
    @NotNull
    @NotBlank
    private String longDescription;
    @NotNull
    @NotBlank
    private String shortDescription;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;
}
