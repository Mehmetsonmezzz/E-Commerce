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


    /* @Override
    public Category findById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new GlobalException("The category with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);
    }*/
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

     /*   Product savedProduct = productRepository.save(product);

     CategoryResponse categoryResponse=CategoryConverter.convertToResponse(product.getCategory());

        return new ProductResponse(
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getImageUrl(),
                savedProduct.getCategory()
                 // CategoryResponse'ı ekleyin

        );*/

        return ProductConverter.convertToResponse(productRepository.save(product));
    }
    /* @Override
    public List<CategoryResponse> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponse> returnList = new ArrayList<>();

        categoryList.forEach(category -> {
            List<ProductResponse> productResponses = ProductConverter.convertListToResponse(category.getProducts());

            returnList.add(new CategoryResponse(category.getId(), category.getName(), category.getImage(),category.getProducts()));
        });
        return returnList;
    }*/

    @Override
    public List<ProductResponse> getAll() {
        List<Product> productList =productRepository.findAll();


      return ProductConverter.convertListToResponse(productList);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }


}
