package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.entity.User.*;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService{

    private  AddressRepository addressRepository;
    private  CityRepository cityRepository;
    private  TownRepository townRepository;
    private  DistrictRepository districtRepository;
    private  QuarterRepository quarterRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              CityRepository cityRepository,
                              TownRepository townRepository,
                              DistrictRepository districtRepository,
                              QuarterRepository quarterRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
        this.quarterRepository = quarterRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);

    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public City getCityByName(String cityName) {
        return cityRepository.findByName(cityName)
                .orElseThrow(() -> new GlobalException("City not found with name: " + cityName, HttpStatus.NOT_FOUND));
    }

    @Override
    public Town getTownByNameAndCity(String townName, City city) {
        return townRepository.findByNameAndCity(townName, city)
                .orElseThrow(() -> new GlobalException("Town not found with name: " + townName + " in city: " + city.getName(),HttpStatus.NOT_FOUND));
    }


    @Override
    public District getDistrictByNameAndTown(String districtName, Town town) {
        return districtRepository.findByNameAndTown(districtName,town)
                .orElseThrow(() ->new GlobalException("District not found with name: "+districtName+"in town"+town,HttpStatus.NOT_FOUND));
    }

    @Override
    public Quarter getQuarterByNameAndDistrict(String quarterName, District district) {
        return quarterRepository.findByNameAndDistrict(quarterName, district)
                .orElseThrow(() -> new GlobalException("Quarter not found with name: " + quarterName + " in district: " + district.getName(),HttpStatus.NOT_FOUND));
    }
    }

