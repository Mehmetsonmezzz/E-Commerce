package com.workintech.ecommerce.dto;

public record AddressRequest  (
        String title,
        String name,
        String surname,
        String phone,
        String townName,
        String cityName,
        String districtName,
        String quarterName
){ }
