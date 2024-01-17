package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.AddressRequest;
import com.workintech.ecommerce.entity.User.*;
import com.workintech.ecommerce.service.User.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Address> addAddress(@RequestBody AddressRequest addressRequest){
       String title = addressRequest.title();
        String name = addressRequest.name();
        String surname = addressRequest.surname();
        String phone = addressRequest.phone();
        String cityName = addressRequest.cityName();
        String townName = addressRequest.townName();
        String districtName = addressRequest.districtName();
        String quarterName = addressRequest.quarterName();


        City city = addressService.getCityByName(cityName);
        Town town = addressService.getTownByNameAndCity(townName, city);
        District district = addressService.getDistrictByNameAndTown(districtName, town);
        Quarter quarter = addressService.getQuarterByNameAndDistrict(quarterName, district);

        Address address = new Address();
        address.setTitle(title);
        address.setName(name);
        address.setSurname(surname);
        address.setPhone(phone);
        address.setTown(town);
        address.setCity(city);
        address.setTown(town);
        address.setDistrict(district);
        address.setQuarter(quarter);

        Address savedAddress = addressService.saveAddress(address);

        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

}
