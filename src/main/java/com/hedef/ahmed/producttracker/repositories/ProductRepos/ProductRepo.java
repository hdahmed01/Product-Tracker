package com.hedef.ahmed.producttracker.repositories.ProductRepos;

import com.hedef.ahmed.producttracker.entity.product.Product;
import com.hedef.ahmed.producttracker.repositories.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepo extends AbstractRepository<Product> {

    public Page<Product> findProductByNameContainsIgnoreCase(String key, Pageable pageable);
    public Page<Product> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String key,String des, Pageable pageable);
}
