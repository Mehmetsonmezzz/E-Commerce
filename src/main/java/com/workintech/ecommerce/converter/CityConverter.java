package com.workintech.ecommerce.converter;

import com.workintech.ecommerce.dto.CityResponse;
import com.workintech.ecommerce.entity.User.City;

import java.util.List;
import java.util.stream.Collectors;

public class CityConverter {
    public static List<CityResponse> convertToCityResponseList(List<City> cities) {
        return cities.stream()
                .map(CityConverter::convertToCityResponse)
                .collect(Collectors.toList());
    }

    public static CityResponse convertToCityResponse(City city) {

        return new CityResponse(city.getName(), city.getAlpha_2_code());
    }
}
