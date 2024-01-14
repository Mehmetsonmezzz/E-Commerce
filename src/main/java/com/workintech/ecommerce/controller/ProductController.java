package com.workintech.ecommerce.controller;


import com.workintech.ecommerce.converter.CategoryConverter;
import com.workintech.ecommerce.converter.ProductConverter;
import com.workintech.ecommerce.dto.CategoryProductRequest;
import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.service.Category.CategoryService;
import com.workintech.ecommerce.service.Product.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService=categoryService;
    }


    @PostMapping("/{categoryId}")
    public ProductResponse save( @PathVariable  long categoryId,@RequestBody Product product ) {

     Category category= categoryService.findById(categoryId);
     if(category!=null){
         product.setCategory(category);
         category.addProduct(product);
         productService.save(product);
     }else {
         throw new GlobalException("Category not found!"+categoryId, HttpStatus.NOT_FOUND);
     }



        CategoryResponse categoryResponse = CategoryConverter.convertToResponse(product.getCategory());

        return new ProductResponse(product.getName(),product.getDescription(),product.getImageUrl(),product.getCategory().getId());
    }
    @PutMapping("/{categoryId}/{productId}")
    public ProductResponse update(@PathVariable long categoryId, @PathVariable long productId, @RequestBody Product updatedProduct) {

        Category category = categoryService.findById(categoryId);
        if (category != null) {

            Product existingProduct = productService.findById(productId);
            if (existingProduct != null) {

                existingProduct.setName(updatedProduct.getName());
                existingProduct.setDescription(updatedProduct.getDescription());
                existingProduct.setImageUrl(updatedProduct.getImageUrl());
                existingProduct.setCategory(category);
                category.addProduct(existingProduct);
                productService.save(existingProduct);
                CategoryResponse categoryResponse = CategoryConverter.convertToResponse(existingProduct.getCategory());
                return new ProductResponse(existingProduct.getName(), existingProduct.getDescription(), existingProduct.getImageUrl(),existingProduct.getCategory().getId());
            } else {
                throw new GlobalException("Product not found! " + productId, HttpStatus.NOT_FOUND);
            }
        } else {
            throw new GlobalException("Category not found! " + categoryId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable long id){
        return ProductConverter.convertToResponse(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        productService.delete(id);
        return "Product id number :"+id+" has been deleted";
    }

}
