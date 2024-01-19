package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(String name, LocalDateTime localDateTime, List<ProductResponse> products) {
}
