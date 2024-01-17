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
@Table(name = "town", schema = "ecommerce")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "town",fetch = FetchType.EAGER)
    private List<District> districts;

    public void addDistrict(District district) {
        if (districts == null) {
            districts = new ArrayList<>();
        }
        districts.add(district);
    }
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="city_id")
    private City city;


}
