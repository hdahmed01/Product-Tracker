package com.hedef.ahmed.producttracker.entity;


import com.hedef.ahmed.producttracker.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private String email;
    private String ville;
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products=new ArrayList<>();


    public Provider(String prenom, String nom, String adresse) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Provider(String nom, String jean, String s, int i, String mail, String paris) {
        this.nom = nom;
        this.prenom = jean;
        this.adresse = paris;
        this.telephone = i;
        this.email = mail;
        this.ville = paris;
    }
}
