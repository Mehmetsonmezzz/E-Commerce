package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.User.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
