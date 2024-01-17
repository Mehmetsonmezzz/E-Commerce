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
@Table(name = "district", schema = "ecommerce")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "district",fetch = FetchType.EAGER)
    private List<Quarter> quarters;

    public void addQuarter(Quarter quarter){
        if (quarters==null){
            quarters=new ArrayList<>();
        }
        quarters.add(quarter);
    }


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "town_id")
    @JsonBackReference
    private Town town;
}
