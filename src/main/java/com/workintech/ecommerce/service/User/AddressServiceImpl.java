package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.dto.AddressRequest;
import com.workintech.ecommerce.entity.User.*;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService{

    private  AddressRepository addressRepository;
    private  CityRepository cityRepository;
    private  TownRepository townRepository;
    private  DistrictRepository districtRepository;
    private  QuarterRepository quarterRepository;

    private UserRepository userRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              CityRepository cityRepository,
                              TownRepository townRepository,
                              DistrictRepository districtRepository,
                              QuarterRepository quarterRepository,UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
        this.quarterRepository = quarterRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);

    }

    @Override
    public Address saveAddress( AddressRequest addressRequest, UserDetails userDetails) {
        String title = addressRequest.title();
        String name = addressRequest.name();
        String surname = addressRequest.surname();
        String phone = addressRequest.phone();
        String cityName = addressRequest.cityName();
        String townName = addressRequest.townName();
        String districtName = addressRequest.districtName();
        String quarterName = addressRequest.quarterName();
        City city = cityRepository.findByName(cityName)
                .orElseThrow(() -> new GlobalException("City not found with : " + cityName, HttpStatus.NOT_FOUND));;
        Town town = townRepository.findByNameAndCity(townName, city)
                .orElseThrow(() -> new GlobalException("Town not found with : " + townName + " in city: " + city.getName(),HttpStatus.NOT_FOUND));
        District district = districtRepository.findByNameAndTown(districtName, town)
                .orElseThrow(() ->new GlobalException("District not found with : "+districtName+"in town"+town,HttpStatus.NOT_FOUND));
        Quarter quarter = quarterRepository.findByNameAndDistrict(quarterName, district)
                .orElseThrow(() -> new GlobalException("Quarter not found with : " + quarterName + " in district: " + district.getName(),HttpStatus.NOT_FOUND));
        ApplicationUser user = (ApplicationUser) userRepository.findUserByEmail(userDetails.getUsername())
                .orElseThrow(() -> new GlobalException("user not found",HttpStatus.NOT_FOUND));



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
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address findById(long id) {
      Optional<Address> address=  addressRepository.findById(id);
      if (address.isPresent()){
          return address.get();
      }
        throw new GlobalException("The address with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);
    }


    @Transactional
    @Override
    public Address delete(long id) {
        Address address = findById(id);
          addressRepository.delete(address);
          return address;
    }


}

