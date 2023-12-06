package com.mrcinkowski.ShoppingApp.api.controller.product;

import com.mrcinkowski.ShoppingApp.api.model.ProductBody;
import com.mrcinkowski.ShoppingApp.api.model.RegistrationBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.exception.UserAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.model.Product;
import com.mrcinkowski.ShoppingApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody ProductBody productBody) {
        System.out.println(productBody.getName());
        try {
            productService.addProduct(productBody);
            return ResponseEntity.ok().build();
        } catch (ProductAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
