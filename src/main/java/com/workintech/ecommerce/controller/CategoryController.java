package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.converter.CategoryConverter;
import com.workintech.ecommerce.dto.CategoryProductRequest;
import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/")
 public CategoryResponse save(@RequestBody Category category){
       return categoryService.save(category);
 }

 @GetMapping("/{id}")
 public CategoryResponse getById(@PathVariable long id){
        return CategoryConverter.convertToResponse(categoryService.findById(id));
    }


 @GetMapping("/")
    public List<CategoryResponse> getAll(){
        return categoryService.getAll();
 }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        categoryService.delete(id);
       return "Category id number "+id+" has been deleted";
    }


}
