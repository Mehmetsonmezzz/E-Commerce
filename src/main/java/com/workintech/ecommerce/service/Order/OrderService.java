package com.workintech.ecommerce.service.Order;

import com.workintech.ecommerce.dto.OrderRequest;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User.City;
import com.workintech.ecommerce.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderService {



    Order save(OrderRequest orderRequest, UserDetails userDetails);

    Order GetById(long id);

}
