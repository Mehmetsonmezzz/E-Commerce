package com.workintech.ecommerce.service.Order;

import com.workintech.ecommerce.dto.OrderRequest;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User.ApplicationUser;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.service.User.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private LoginUserService loginUserService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,ProductRepository productRepository,LoginUserService loginUserService) {
        this.orderRepository = orderRepository;
        this.productRepository=productRepository;
        this.loginUserService=loginUserService;
    }

    @Override
    public Order save(OrderRequest orderRequest, UserDetails userDetails) {
        String name = orderRequest.name();
        List<String> products = orderRequest.productsName();

        ApplicationUser user = (ApplicationUser) loginUserService.loadUserByUsername(userDetails.getUsername());

        Order order = new Order();
        order.setName(name);
        order.setUser(user);
        List<Product> productList = new ArrayList<>();
        for (String productName : products) {
            Product product = productRepository.findByName(productName)
                    .orElseThrow(() -> new GlobalException("Product not found with : " + productName, HttpStatus.NOT_FOUND));
            productList.add(product);
        }
        order.setProductList(productList);

        return orderRepository.save(order);
    }

    @Override
    public Order GetById(long id) {
        Optional<Order> optionalOrder=orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        throw new GlobalException("The order with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);

    }


}
