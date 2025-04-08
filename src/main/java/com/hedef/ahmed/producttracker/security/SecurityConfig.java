package com.hedef.ahmed.producttracker.security;

import com.hedef.ahmed.producttracker.security.service.UserDetailsServiceImp;
import groovy.transform.AutoImplement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity

@RequiredArgsConstructor
public class SecurityConfig {


    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImp userDetailsServiceImp;

//    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
        );
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/products/admin/**").hasRole("ADMIN") // Only ADMIN can access
                        .requestMatchers("/products/user/**").hasRole("USER")   // Only USER can access
                        .requestMatchers("/webjars/**").permitAll()// Only USER can access
                        .anyRequest().authenticated()

                        // All other requests require authentication
                ).exceptionHandling(exception -> exception
                        .accessDeniedPage("/notAutorized"))
                .formLogin(withDefaults()) //defaultsuccesurl
                .rememberMe(rememberMe -> rememberMe
                        .key("uniqueAndSecret") // Secret key for encryption
                        .tokenValiditySeconds(7 * 24 * 60 * 60))// Enable default login form
                .logout(withDefaults())
                .userDetailsService(userDetailsServiceImp);
        return  http.build();
    }
}
