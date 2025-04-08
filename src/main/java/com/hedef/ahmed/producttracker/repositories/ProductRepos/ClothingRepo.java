package com.hedef.ahmed.producttracker.repositories.ProductRepos;

import com.hedef.ahmed.producttracker.entity.Provider;
import com.hedef.ahmed.producttracker.entity.product.Clothing;
import com.hedef.ahmed.producttracker.entity.product.Electronics;
import com.hedef.ahmed.producttracker.repositories.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClothingRepo extends AbstractRepository<Clothing> {
    public Page<Clothing> findProductByNameContainsIgnoreCase(String key, Pageable pageable);
    public Page<Clothing> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String key,String des, Pageable pageable);

}
