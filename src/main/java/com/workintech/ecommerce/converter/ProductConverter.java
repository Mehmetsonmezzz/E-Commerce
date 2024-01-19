package com.workintech.ecommerce.converter;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static ProductResponse convertToResponse(Product product) {
        return new ProductResponse( product.getName(), product.getDescription(),product.getImageUrl());
    }

    public static List<ProductResponse> convertListToResponse(List<Product> productList) {
        return productList.stream()
                .map(ProductConverter::convertToResponse)
                .collect(Collectors.toList());
    }
}