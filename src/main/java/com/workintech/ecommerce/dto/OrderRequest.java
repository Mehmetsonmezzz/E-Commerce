package com.workintech.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(String name,  List<String> productsName) {
}
