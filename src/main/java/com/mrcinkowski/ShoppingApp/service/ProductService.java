package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.model.ProductBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.model.Inventory;
import com.mrcinkowski.ShoppingApp.model.Product;
import com.mrcinkowski.ShoppingApp.model.repository.InventoryRepository;
import com.mrcinkowski.ShoppingApp.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductService(ProductRepository productRepository,
                          InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
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
