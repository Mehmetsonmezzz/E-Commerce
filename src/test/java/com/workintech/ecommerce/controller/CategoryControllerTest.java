package com.workintech.ecommerce.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.service.Category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;


    @Test
    void save() throws Exception {
        Category category = new Category();
        category.setName("ayakkabı");
        category.setImage("hizliresim.com");

        //Stubbing
        when(categoryService.save(category)).thenReturn(category);

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(category))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ayakkabı"));

        verify(categoryService).save(category);
    }

    public static String jsonToString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}