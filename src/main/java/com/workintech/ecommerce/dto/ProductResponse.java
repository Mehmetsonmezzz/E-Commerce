package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Category;

import java.util.List;

public record ProductResponse(String name, String description, List<String> image, Long categoryId) {
}
