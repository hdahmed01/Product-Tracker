package com.hedef.ahmed.producttracker.security.service;

import com.hedef.ahmed.producttracker.security.entity.AppRole;
import com.hedef.ahmed.producttracker.security.entity.AppUser;
import com.hedef.ahmed.producttracker.security.repo.AppRoleRepo;
import com.hedef.ahmed.producttracker.security.repo.AppUserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AcountServiceImpl implements AcountService {

    private AppRoleRepo appRoleRepo;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addUser(String username, String password, String email, String confirmPassword) {
        AppUser user= appUserRepo.findByUsername(username);
        if(user!=null){throw new RuntimeException("User already exists");}
       if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");

       user = AppUser.builder().username(username).password(passwordEncoder.encode(password)).email(email).build();
       appUserRepo.save(user);
       return user;
    }

    @Override
    public AppRole addRole(String roleName) {
        AppRole appRol=appRoleRepo.findById(roleName).orElse(null);
        if(appRol!=null) throw new RuntimeException("Role  already exists");
        return  appRoleRepo.save(new AppRole(roleName));
    }

    @Override
    public void removeRoleFromUser(String userName, String roleName) {
        Optional<AppUser> appUserOp=appUserRepo.findById(userName);
        if(appUserOp.isEmpty()) { throw new RuntimeException("User not found"); }
        AppUser appUser=appUserOp.get();
        AppRole appRole=appRoleRepo.findById(roleName).orElse(null);
        appUser.getRoles().remove(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
       Optional<AppUser> appUserOp=Optional.of(appUserRepo.findByUsername(userName));
       if(appUserOp.isEmpty()) { throw new RuntimeException("User not found"); }

       AppUser appUser=appUserOp.get();
       AppRole appRole=appRoleRepo.findById(roleName).orElse(null);
       appUser.getRoles().add(appRole);
    }

    @Override
    public AppUser  loadUserByUsername(String username) {
        AppUser appUser=appUserRepo.findByUsername(username);
        if(appUser==null) { throw new RuntimeException("User not found"); }
        return appUser;
    }
}
