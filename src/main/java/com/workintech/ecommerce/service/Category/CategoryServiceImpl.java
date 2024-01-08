package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

   private CategoryRepository categoryRepository;


   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse save(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getName(),savedCategory.getImage());
    }

    @Override
    public CategoryResponse findById(long id) {
       Optional<Category> optionalCategory=categoryRepository.findById(id);
       if(optionalCategory.isPresent()){
           Category selectedCategory=optionalCategory.get();
           return new CategoryResponse(selectedCategory.getId(),
                   selectedCategory.getName(),
                   selectedCategory.getImage());
       }
       return null;
    }

    @Override
    public CategoryResponse delete(long id) {
     CategoryResponse selectedCategory = findById(id);
     categoryRepository.deleteById(selectedCategory.id());
        return selectedCategory;
    }
}
