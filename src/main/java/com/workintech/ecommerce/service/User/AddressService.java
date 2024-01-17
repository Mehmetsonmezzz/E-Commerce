package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.entity.User.*;

public interface AddressService {


    Address save(Address address);

    Address saveAddress(Address address);

    City getCityByName(String cityName);

    Town getTownByNameAndCity(String townName, City city);

    District getDistrictByNameAndTown(String districtName, Town town);

    Quarter getQuarterByNameAndDistrict(String quarterName, District district);
}

