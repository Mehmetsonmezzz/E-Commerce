package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User.City;
import com.workintech.ecommerce.entity.User.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TownRepository  extends JpaRepository<Town, Long> {

    @Query("SELECT t FROM Town t WHERE t.name= :name AND t.city = :city")
    Optional<Town> findByNameAndCity(String name, City city);
}
