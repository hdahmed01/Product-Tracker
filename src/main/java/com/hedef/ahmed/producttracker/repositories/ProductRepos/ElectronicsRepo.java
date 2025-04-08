package com.hedef.ahmed.producttracker.repositories.ProductRepos;

import com.hedef.ahmed.producttracker.entity.Provider;
import com.hedef.ahmed.producttracker.entity.product.Electronics;
import com.hedef.ahmed.producttracker.entity.product.Product;
import com.hedef.ahmed.producttracker.repositories.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElectronicsRepo extends AbstractRepository<Electronics> {

    public Page<Electronics> findProductByNameContainsIgnoreCase(String key, Pageable pageable);


}
