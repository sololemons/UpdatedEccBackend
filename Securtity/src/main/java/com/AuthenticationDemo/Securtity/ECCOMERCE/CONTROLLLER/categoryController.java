package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;



import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.categoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Category")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class categoryController {

    private   categoryServices categoryServices;

    @Autowired


    public categoryController(categoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> GetCategory() {
        return categoryServices.getAllCategories();
    }

    @PostMapping("/add")
    public Category addNewCategory(@RequestBody Category category) {
        return categoryServices.addCategory(category);
    }

    @PutMapping("update/id/{categoryId}")
    public void changeCategoryCredentials(@PathVariable Integer categoryId, @RequestBody Category categoryDetails){

        categoryServices.updateCategory(categoryId,categoryDetails);
    }

    @DeleteMapping("/Delete/id/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId) {
        boolean deleted = categoryServices.deleteCategoryById(categoryId);
        if (deleted) {
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }

    }
}