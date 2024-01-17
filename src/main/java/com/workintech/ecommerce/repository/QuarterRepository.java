package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User.District;
import com.workintech.ecommerce.entity.User.Quarter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuarterRepository extends JpaRepository<Quarter,Long> {
    @Query("SELECT q FROM Quarter q WHERE q.name = :name AND q.district = :district")
    Optional<Quarter> findByNameAndDistrict(String name, District district);
}
