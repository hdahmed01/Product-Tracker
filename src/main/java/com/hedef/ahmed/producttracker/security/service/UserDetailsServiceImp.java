package com.hedef.ahmed.producttracker.security.service;

import com.hedef.ahmed.producttracker.security.entity.AppRole;
import com.hedef.ahmed.producttracker.security.entity.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UserDetailsServiceImp implements UserDetailsService {


    private final AcountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);


        if (appUser == null) {throw new UsernameNotFoundException(String.format("User %s not Found",username));}
        String[] roles = appUser.getRoles().stream().map(AppRole::getRole).toArray(String[]::new);
        return User.withUsername(appUser.getUsername()).password(appUser.getPassword()).roles(roles).build();
    }
}
