package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    Category findById(long id);

    List<CategoryResponse> getAll();

    void delete(long id);





}
