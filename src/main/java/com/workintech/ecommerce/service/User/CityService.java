package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.dto.CityResponse;
import com.workintech.ecommerce.entity.User.City;

import java.util.List;

public interface CityService {


    List<City> SaveAll(List<City> city);
    City FindById(long id);

    City FindByAlpha(String alpha);
}
