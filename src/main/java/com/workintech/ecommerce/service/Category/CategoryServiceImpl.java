package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.converter.ProductConverter;
import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

   private CategoryRepository categoryRepository;


   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        for (Product product : category.getProducts()) {
            product.setCategory(category);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new GlobalException("The category with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponse> returnList = new ArrayList<>();

        categoryList.forEach(category -> {
            List<ProductResponse> productResponses = ProductConverter.convertListToResponse(category.getProducts());

            returnList.add(new CategoryResponse( category.getName(), category.getImage(),ProductConverter.convertListToResponse(category.getProducts())));
        });
        return returnList;
    }

    @Override
    public void delete(long id) {
       Optional<Category> category=categoryRepository.findById(id);
       if(category.isPresent()){
           categoryRepository.deleteById(id);
       }else {
           throw new GlobalException("id not exist", HttpStatus.NOT_FOUND);
       }

    }
}
