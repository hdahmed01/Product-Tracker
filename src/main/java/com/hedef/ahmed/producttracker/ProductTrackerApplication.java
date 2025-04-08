package com.hedef.ahmed.producttracker;

import com.hedef.ahmed.producttracker.entity.Provider;
import com.hedef.ahmed.producttracker.entity.product.Clothing;
import com.hedef.ahmed.producttracker.repositories.ProductRepos.ClothingRepo;
import com.hedef.ahmed.producttracker.repositories.ProviderRepo;
import com.hedef.ahmed.producttracker.security.entity.AppUser;
import com.hedef.ahmed.producttracker.security.service.AcountService;
import com.hedef.ahmed.producttracker.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class ProductTrackerApplication implements CommandLineRunner{

    @Autowired
    ProviderRepo providerRepo;
    @Autowired
    ClothingRepo clothingRepo;
    @Autowired
    ProductService productService;
    @Autowired
    ClothingRepo clothing;



    public static void main(String[] args) {
        SpringApplication.run(ProductTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Provider provider1 = new Provider("ahmed", "Jean", "10 Rue de Paris", 123456789, "jean.dupont@example.com", "Paris");
////        Provider provider2 = new Provider(2L, "Martin", "Sophie", "25 Avenue Victor Hugo", 987654321, "sophie.martin@example.com", "Lyon",null);
////        Provider provider3 = new Provider(3L, "Lefevre", "Paul", "5 Boulevard Haussmann", 111222333, "paul.lefevre@example.com", "Marseille",null);
////        Provider provider4 = new Provider(4L, "Durand", "Claire", "15 Place Bellecour", 444555666, "claire.durand@example.com", "Toulouse",null);
//        providerRepo.save(provider1);
//        providerRepo.save(provider2);
//        providerRepo.save(provider3);
//        providerRepo.save(provider4);
//        providerRepo.save(new Provider("ahmed","hedef","Tunise"));
//        productService.saveProduct(new Clothing("s","yello",25));
        //productService.saveProduct(new Clothing("s","white",15,"shirt",10.0,"clothes",20,provider));
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
    public CommandLineRunner init( JdbcUserDetailsManager jdbcUserDetailsManager){
        return args -> {
            jdbcUserDetailsManager.createUser(User.withUsername("ahmed").password(passwordEncoder().encode("123")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123")).roles("USER","ADMIN").build());

        };
    }
    @Bean
    public CommandLineRunner loadUsers( AcountService acountService){
        return args -> {
//          acountService.addRole("USER");
//          acountService.addRole("ADMIN");
//          acountService.addUser("user","123","user@gmail.com","123");
//          acountService.addUser("admin","123","user@gmail.com","123");
//          acountService.addUser("use3","123","user@gmail.com","123");
//          acountService.addRoleToUser("user","USER");
//          acountService.addRoleToUser("use2","USER");
//          acountService.addRoleToUser("use3","USER");
          acountService.addRoleToUser("admin","USER");
//            AppUser appUser = acountService.loadUserByUsername("use1");
        };
    }
}
