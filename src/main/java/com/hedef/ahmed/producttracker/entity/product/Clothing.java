package com.hedef.ahmed.producttracker.entity.product;


import com.hedef.ahmed.producttracker.entity.Provider;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Clothing extends Product {


    private String size;
    private String color;
    private int age;

    public Clothing(String size, String color, int age, String name, double price, String description,int quantity,Provider provider) {
        super(null,name,description,price,quantity,provider);
        this.size = size;
        this.color = color;
        this.age = age;
    }

}
