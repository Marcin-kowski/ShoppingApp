package com.mrcinkowski.ShoppingApp.service.implementations;

import com.mrcinkowski.ShoppingApp.api.dto.ProductBody;
import com.mrcinkowski.ShoppingApp.exception.ProductAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.exception.ProductNotFoundException;
import com.mrcinkowski.ShoppingApp.repository.InventoryRepository;
import com.mrcinkowski.ShoppingApp.repository.ProductRepository;
import com.mrcinkowski.ShoppingApp.repository.entities.InventoryEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.ProductEntity;
import com.mrcinkowski.ShoppingApp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProduct(long productId) throws ProductNotFoundException {
        Optional<ProductEntity> product = productRepository.findById(productId);
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

    public ProductEntity addProduct(ProductBody productBody) throws ProductAlreadyExistsException {
        if (productRepository.findByNameIgnoreCase(productBody.getName()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productBody.getName());
        productEntity.setShortDescription(productBody.getShortDescription());
        productEntity.setLongDescription(productBody.getLongDescription());
        productEntity.setPrice(productBody.getPrice());
        productRepository.save(productEntity);
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setProductEntity(productEntity);
        inventoryEntity.setQuantity(productBody.getQuantity());
        inventoryRepository.save(inventoryEntity);
        productEntity.setInventoryEntity(inventoryEntity);
        return productRepository.save(productEntity);
    }




}
