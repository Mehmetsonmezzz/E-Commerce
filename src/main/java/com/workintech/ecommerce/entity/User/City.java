package com.workintech.ecommerce.entity.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "city", schema = "ecommerce")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "alpha_2_code")
    private String alpha_2_code;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    private List<Town> towns;

    public void addTown(Town town) {
        if (towns == null) {
            towns = new ArrayList<>();
        }
        towns.add(town);
    }

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    private List<Address> addresses;

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(address);
    }



}
