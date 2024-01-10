package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.converter.ProductConverter;
import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ProductResponse> productResponses = ProductConverter.convertListToResponse(savedCategory.getProducts());

        return new CategoryResponse(savedCategory.getId(), savedCategory.getName(),savedCategory.getImage(),productResponses);
    }

    @Override
    public CategoryResponse findById(long id) {
       Optional<Category> optionalCategory=categoryRepository.findById(id);

       if(optionalCategory.isPresent()){
           Category selectedCategory=optionalCategory.get();
           List<ProductResponse> productResponses = ProductConverter.convertListToResponse(selectedCategory.getProducts());

           return new CategoryResponse(selectedCategory.getId(),
                   selectedCategory.getName(),
                   selectedCategory.getImage(),productResponses);
       }
       return null;
    }

    @Override
    public CategoryResponse getAll() {

        return ;
    }

    @Override
    public CategoryResponse delete(long id) {
     CategoryResponse selectedCategory = findById(id);
     categoryRepository.deleteById(selectedCategory.id());
        return selectedCategory;
    }
}
