package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.dto.CategoryProductRequest;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/")
    public ProductResponse save(@RequestBody CategoryProductRequest productCategoryRequest){


    return null;
    }

}
