package com.workintech.ecommerce.service.Product;

import com.workintech.ecommerce.converter.CategoryConverter;
import com.workintech.ecommerce.converter.ProductConverter;
import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.GlobalException;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.service.Product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository=categoryRepository;
    }


    @Override
    public Product findById(long id) {
      Optional<Product> productOptional= productRepository.findById(id);
      if (productOptional.isPresent()){
          return productOptional.get();
      }
      //Todo throw
        throw  new  GlobalException("Product not found"+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ProductResponse save(Product product) {



        return ProductConverter.convertToResponse(productRepository.save(product));
    }


    @Override
    public List<ProductResponse> getAll() {
        List<Product> productList =productRepository.findAll();


      return ProductConverter.convertListToResponse(productList);
    }


    @Transactional
    @Override
    public void delete(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new GlobalException("id not exist", HttpStatus.NOT_FOUND);
        }
    }


}
