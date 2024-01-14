package com.workintech.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "category", schema = "ecommerce")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@Max(value = 25,message = "Name cannot exceed 25 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category",fetch = FetchType.EAGER)
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }


}
