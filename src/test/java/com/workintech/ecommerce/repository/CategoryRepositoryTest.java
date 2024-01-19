package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    private void createCategory(String name){
        Category category =new Category();
        category.setName(name);
        category.setImage("hizliresim.com/deneme");
        Category foundCategory=categoryRepository.findByName(name);
        if(foundCategory==null){
            categoryRepository.save(category);
        }
    }

    @BeforeEach
    void setUp(){
        createCategory("test");
    }

    @AfterEach
    void tearDown(){
        categoryRepository.deleteAll();
    }

    @Test
    void findByName(){
        Category selectCategory=categoryRepository.findByName("test");
        assertNotNull(selectCategory);
        assertEquals("hizliresim.com/deneme",selectCategory.getImage());

    }

    @Test
    void findByNameFail(){
        Category selectCategory=categoryRepository.findByName("test2");
        assertNull(selectCategory);

    }
}