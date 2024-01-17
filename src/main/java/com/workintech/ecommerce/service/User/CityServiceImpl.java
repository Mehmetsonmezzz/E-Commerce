package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.controller.CityController;
import com.workintech.ecommerce.dto.CityResponse;
import com.workintech.ecommerce.entity.User.City;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<City> SaveAll(List<City> city) {
        return cityRepository.saveAll(city);
    }

    @Override
    public City FindById(long id) {
       Optional<City> city= cityRepository.findById(id);
        if(city.isPresent()){
            return city.get();
        }
        throw new GlobalException("The city with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public City FindByAlpha(String alpha) {

        return cityRepository.findCityByAlpha(alpha);
    }
}
