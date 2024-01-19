package com.workintech.ecommerce.service.User;

import com.workintech.ecommerce.dto.AddressRequest;
import com.workintech.ecommerce.entity.User.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AddressService {


    Address save(Address address);

    Address saveAddress(AddressRequest addressRequest, UserDetails userDetails);

    Address findById(long id);

    Address delete(long id);
}

