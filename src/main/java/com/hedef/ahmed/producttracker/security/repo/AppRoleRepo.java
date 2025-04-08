package com.hedef.ahmed.producttracker.security.repo;

import com.hedef.ahmed.producttracker.security.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);
}
