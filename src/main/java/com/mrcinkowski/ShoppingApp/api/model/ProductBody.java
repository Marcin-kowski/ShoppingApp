package com.mrcinkowski.ShoppingApp.api.model;

import com.mrcinkowski.ShoppingApp.model.Inventory;
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
