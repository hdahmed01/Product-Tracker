package com.hedef.ahmed.producttracker.repositories;

import com.hedef.ahmed.producttracker.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepo extends AbstractRepository<Provider> {


    Page<Provider> findProviderByNomContainingIgnoreCase(String nom,Pageable pageable);
    Page<Provider> findProviderByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrVilleContainingIgnoreCase(String nom,String p,String v,Pageable pageable);

}
