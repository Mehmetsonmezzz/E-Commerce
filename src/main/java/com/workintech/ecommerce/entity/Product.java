package com.workintech.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.workintech.ecommerce.entity.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product",schema = "ecommerce")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "gender")
    private Gender gender;


    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private List<String> imageUrl;


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;

    /*@JsonManagedReference
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="order_product",schema = "ecommerce",joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    private List<Order> orderList=new ArrayList<>();

    public void addOrder(Order order){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        orderList.add(order);
    }*/
}
