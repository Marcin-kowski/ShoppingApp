package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.dto.ProductBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.exception.ProductNotFoundException;
import com.mrcinkowski.ShoppingApp.model.Inventory;
import com.mrcinkowski.ShoppingApp.model.Product;
import com.mrcinkowski.ShoppingApp.model.repository.InventoryRepository;
import com.mrcinkowski.ShoppingApp.model.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductService(ProductRepository productRepository,
                          InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("id-" + productId);
        }
        return product;
    }
    public void deleteProduct(long productId) throws ProductNotFoundException {
        if (productRepository.findById(productId).isEmpty()) {
            throw new ProductNotFoundException("id-" + productId);
        }
        productRepository.deleteById(productId);
    }

    public Product addProduct(ProductBody productBody) throws ProductAlreadyExistsException {
        if (productRepository.findByNameIgnoreCase(productBody.getName()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        Product product = new Product();

        product.setName(productBody.getName());
        product.setShortDescription(productBody.getShortDescription());
        product.setLongDescription(productBody.getLongDescription());
        product.setPrice(productBody.getPrice());
        productRepository.save(product);
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(productBody.getQuantity());
        inventoryRepository.save(inventory);
        product.setInventory(inventory);
        return productRepository.save(product);
    }




}
