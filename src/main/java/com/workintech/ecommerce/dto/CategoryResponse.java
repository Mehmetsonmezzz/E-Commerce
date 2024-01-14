package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum.Gender;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public record CategoryResponse(Long id, String name, String image,List<Product> products) {
}
