package com.hedef.ahmed.producttracker.security.service;

import com.hedef.ahmed.producttracker.security.entity.AppRole;
import com.hedef.ahmed.producttracker.security.entity.AppUser;

public interface AcountService {

    public AppUser addUser(String username, String password,String email,String confirmPassword);
    AppRole addRole(String roleName);
    void removeRoleFromUser(String userName,String roleName);
    void addRoleToUser(String userName,String roleName);
    AppUser loadUserByUsername(String username);
}
