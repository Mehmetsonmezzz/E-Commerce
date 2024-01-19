package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.AddressRequest;
import com.workintech.ecommerce.dto.AddressResponse;
import com.workintech.ecommerce.entity.User.*;
import com.workintech.ecommerce.service.User.AddressService;
import com.workintech.ecommerce.service.User.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;

    }


    @PostMapping("/")
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest, @AuthenticationPrincipal UserDetails userDetails){

        Address savedAddress = addressService.saveAddress(addressRequest,userDetails);
        AddressResponse addressResponse=new AddressResponse(savedAddress.getTitle(),savedAddress.getName(),savedAddress.getSurname(), savedAddress.getPhone());
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public Address delete(@PathVariable long id){

        return  addressService.delete(id);
    }

}
