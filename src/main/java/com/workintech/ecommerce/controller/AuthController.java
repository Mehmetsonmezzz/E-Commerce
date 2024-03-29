package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.RegisterResponse;
import com.workintech.ecommerce.dto.RegisterUser;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import com.workintech.ecommerce.service.User.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterUser registerUser){

    return authenticationService.register(registerUser.fullName(),registerUser.email(),registerUser.password());
}

}
