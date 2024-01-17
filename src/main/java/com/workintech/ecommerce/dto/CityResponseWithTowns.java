package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.User.District;
import com.workintech.ecommerce.entity.User.Quarter;
import com.workintech.ecommerce.entity.User.Town;

import java.util.List;

public record CityResponseWithTowns(String name, String AlphaCode, List<Town> towns) {
}
