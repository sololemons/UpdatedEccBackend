package com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES;
import com.AuthenticationDemo.Securtity.ECCOMERCE.DTO.ProductDto;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class productServices {

    private productRepository productRepository;

    @Autowired

    public productServices(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createNewProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage_Url(productDto.getImage_Url());
        product.setCategory(category);
        productRepository.save(product);
    }

    public void updateProduct(ProductDto productDto, Integer product_id) {
        Optional<Product> optionalProduct = productRepository.findById(product_id);
        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setImage_Url(productDto.getImage_Url());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);
        }


    }

    public void deleteProductByproductId(Integer productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }
}




