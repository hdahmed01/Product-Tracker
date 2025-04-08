package com.hedef.ahmed.producttracker.entity.product;

import com.hedef.ahmed.producttracker.entity.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public   class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name cannot be Empty")
     private String name;
     private String description;
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be at least 1")
     private double price;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
     @ManyToOne
     private Provider provider;

}
