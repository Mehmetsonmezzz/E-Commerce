package com.workintech.ecommerce.service.Product;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    Product findById(long id);
    ProductResponse save(Product product);

    List<ProductResponse> getAll();

    void delete(long id);
}
