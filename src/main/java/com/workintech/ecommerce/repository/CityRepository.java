package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {



    @Query("SELECT c FROM City c WHERE c.alpha_2_code = :alphaCode")
    City findCityByAlpha(@Param("alphaCode") String alphaCode);


    @Query("SELECT c FROM City c WHERE c.name = :name")
    Optional<City> findByName(String name);
}
