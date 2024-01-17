package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.UserResponseForEmail;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import com.workintech.ecommerce.service.User.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private LoginUserService loginUserService;

    @Autowired
    public UserController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping("/{email}")
    public UserResponseForEmail loadUserByUsername(@PathVariable  String email){
        UserDetails user=  loginUserService.loadUserByUsername(email);
      return new UserResponseForEmail(user.getUsername(),user.getAuthorities());
   }
}
