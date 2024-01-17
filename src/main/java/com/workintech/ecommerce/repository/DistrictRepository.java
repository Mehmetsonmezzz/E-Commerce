package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User.District;
import com.workintech.ecommerce.entity.User.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District,Long> {


    @Query("SELECT d FROM District d WHERE d.name= :name AND d.town= :town")
    Optional<District> findByNameAndTown(String name, Town town);



}
