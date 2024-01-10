package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class CategoryProductRequest {

    private Category category;
    private List<Product> products;
}
