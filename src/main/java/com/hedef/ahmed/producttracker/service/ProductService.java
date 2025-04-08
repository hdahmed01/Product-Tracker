package com.hedef.ahmed.producttracker.service;

import com.hedef.ahmed.producttracker.entity.product.Product;
import com.hedef.ahmed.producttracker.repositories.ProductRepos.ProductRepo;
import com.hedef.ahmed.producttracker.repositories.ProviderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Optional<Product> saveProduct(Product product) {
        return Optional.of(productRepo.save(product));
    }
    public Optional<Product> updateProduct(Product product) {
        return Optional.of(productRepo.save(product));
    }
    public Optional<Product> getProduct(Long id) {
        return  productRepo.findById(id);
    }
    public Optional<Product> deleteProduct(Long id) {
        return productRepo.findById(id)
                .map(product -> {
                    productRepo.delete(product);
                    return product;
                });
    }


    public Optional<List<Product>> getAllProducts() {
        return Optional.of(productRepo.findAll());
    }

}
