package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.CityResponse;
import com.workintech.ecommerce.dto.CityResponseWithTowns;
import com.workintech.ecommerce.entity.User.City;
import com.workintech.ecommerce.service.User.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/saveAll")
    public List<City> saveAll(@RequestBody List<City> city){
        return cityService.SaveAll(city);
    }

    @GetMapping("/{id}")
    public CityResponse getCityById(@PathVariable long id){
      City city=  cityService.FindById(id);
      return new CityResponse(city.getName(),city.getAlpha_2_code());
    }

    @GetMapping
    public CityResponseWithTowns getCityByAlphaCode(@RequestParam String alpha){
        City city= cityService.FindByAlpha(alpha);

        return new CityResponseWithTowns(city.getName(),city.getAlpha_2_code(),city.getTowns());
      //  return cityService.FindByAlpha(alpha);
    }
}
