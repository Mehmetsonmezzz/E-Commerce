package com.workintech.ecommerce.service.Product;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse findById(long id) {
      Optional<Product> productOptional= productRepository.findById(id);
      if (productOptional.isPresent()){
          return new ProductResponse(productOptional.get().getName(),productOptional.get().getDescription(),productOptional.get().getImageUrl()) ;
      }
      //Todo throw
        return null ;
    }
}
