package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.dto.UserResponseForEmail;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser,Long>
{


    @Query("SELECT u From ApplicationUser u WHERE u.email= :email")
    Optional<UserDetails> findUserByEmail(String email);
}
