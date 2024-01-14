package com.workintech.ecommerce.converter;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static CategoryResponse convertToResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getImage(),category.getProducts());
    }

    public static List<ProductResponse> convertListToResponse(List<Product> productList) {
        return productList.stream()
                .map(ProductConverter::convertToResponse).toList();

}
}
