package com.workintech.ecommerce.dto;

import java.util.List;

public record ProductResponse(String name, String description, List<String> image) {
}
