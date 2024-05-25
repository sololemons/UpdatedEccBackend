package com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES;

import com.AuthenticationDemo.Securtity.ECCOMERCE.EXCEPTIONS.ResourceNotFoundException;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class categoryServices {
  private categoryRepository categoryRepository;

    @Autowired
    public categoryServices(categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    public Category addCategory(Category category) {

        return categoryRepository.save(category);

    }

    public boolean deleteCategoryById(String categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(Integer.valueOf(categoryId));
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(Integer.valueOf(categoryId));
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<Category> updateCategory(Integer categoryId, Category categoryDetails) {

        Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Person does not exist with id :" + categoryId));
        updateCategory.setName(categoryDetails.getName());
        updateCategory.setDescription(categoryDetails.getDescription());
        updateCategory.setImage_Url(categoryDetails.getImage_Url());
        categoryRepository.save(updateCategory);
        return ResponseEntity.ok(updateCategory);
    }

}



