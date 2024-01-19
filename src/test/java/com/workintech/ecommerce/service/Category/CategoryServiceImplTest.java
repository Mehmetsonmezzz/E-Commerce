package com.workintech.ecommerce.service.Category;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest
 @ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
private CategoryService categoryService;
@Mock
private CategoryRepository categoryRepository;

@BeforeEach
     void setUp(){
    categoryService=new CategoryServiceImpl(categoryRepository);
}


    @Test
     void canSave(){
        Category category=new Category();
        category.setName("deneme");
        category.setImage("hizliresim.com");
        Product product=new Product();
        List<Product> products= new ArrayList<>();
        products.add(product);
        category.setProducts(products);
        categoryService.save(category);
        verify(categoryRepository).save(category);
    }




}