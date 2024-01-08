package com.workintech.ecommerce.service.Product;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;

public interface ProductService {

    ProductResponse findById(long id);
}
