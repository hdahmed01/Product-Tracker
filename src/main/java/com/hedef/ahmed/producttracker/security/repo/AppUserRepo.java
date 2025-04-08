package com.hedef.ahmed.producttracker.security.repo;

import com.hedef.ahmed.producttracker.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);
}
