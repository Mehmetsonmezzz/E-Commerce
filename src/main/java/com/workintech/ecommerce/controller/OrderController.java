package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.converter.ProductConverter;
import com.workintech.ecommerce.dto.OrderRequest;
import com.workintech.ecommerce.dto.OrderResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import com.workintech.ecommerce.service.Order.OrderService;
import com.workintech.ecommerce.service.User.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }


    @PostMapping("/")
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Order savedOrder = orderService.save(orderRequest, userDetails);
        return new OrderResponse(savedOrder.getName(),savedOrder.getOrderDateTime(),ProductConverter.convertListToResponse(savedOrder.getProductList()));
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable long id){
        Order getOrder=orderService.GetById(id);
        return new OrderResponse(getOrder.getName(),getOrder.getOrderDateTime(),ProductConverter.convertListToResponse(getOrder.getProductList()));
    }
}
