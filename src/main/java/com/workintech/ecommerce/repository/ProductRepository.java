package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Enum.Gender;
import com.workintech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("SELECT p From Product p WHERE p.gender= :gender")
    Product findProductByGender(long gender);

    @Query("SELECT p From Product p WHERE p.name= :name")
    Optional<Product> findByName(String name);

    //@Query("SELECT c FROM City c WHERE c.name = :name")
    //    Optional<City> findByName(String name);
}
