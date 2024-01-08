package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.entity.Category;

public interface CategoryService {
    CategoryResponse save(Category category);
    CategoryResponse findById(long id);

    CategoryResponse delete(long id);





}
