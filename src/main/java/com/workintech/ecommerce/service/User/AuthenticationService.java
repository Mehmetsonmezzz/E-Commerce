package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.dto.RegisterResponse;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import com.workintech.ecommerce.entity.User.Role;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.RoleRepository;
import com.workintech.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
   public RegisterResponse register(String fullName, String email, String password){
       String encodedPassword = passwordEncoder.encode(password);
       Role userRole=roleRepository.findByAuthority("USER")
               .orElseThrow(() -> new RuntimeException("Default role not found!"));
       ;
       Set<Role> roles =new HashSet<>();
       roles.add(userRole);


       ApplicationUser user =new ApplicationUser();
       user.setFullName(fullName);
       user.setEmail(email);
       user.setPassword(encodedPassword);
       user.setAuthority(roles);
       userRepository.save(user);
       return new  RegisterResponse("Success",user.getFullName(),user.getEmail()) ;
    }


}
