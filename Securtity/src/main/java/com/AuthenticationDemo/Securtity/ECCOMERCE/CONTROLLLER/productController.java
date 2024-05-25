package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;
import com.AuthenticationDemo.Securtity.ECCOMERCE.DTO.ProductDto;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.categoryRepository;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.productServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v2/Product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class productController {


    private productServices productServices;
@Autowired
    public productController(productServices productServices) {
        this.productServices = productServices;
    }




    @Autowired
    private categoryRepository categoryRepository;





    @GetMapping("/Get")
    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }
    @GetMapping("/Get/id/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable Integer categoryId) {
        return productServices.getProductsByCategoryId(categoryId);
    }

    @PostMapping("/Add")
    public ResponseEntity<ProductDto>addNewProduct(@RequestBody ProductDto productDto){
        Optional<Category> ID= categoryRepository.findById(productDto.getCategoryId());
        if (ID.isPresent()){
            productServices.createNewProduct(productDto,ID.get());
            return ResponseEntity.ok().body(productDto);

        }
        return ResponseEntity.notFound().build();


    }
    @PutMapping("Update/id/{product_id}")
    public ResponseEntity<ProductDto>editProduct(@RequestBody ProductDto productDto,@PathVariable Integer product_id)  {
        Optional<Category> optionalProduct = categoryRepository.findById(productDto.getCategoryId());
        if (optionalProduct.isPresent()){
            productServices.updateProduct(productDto,product_id);
            return ResponseEntity.ok().body(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("Delete/{product_id}")
    public String deleteProductByProductId(@PathVariable Integer product_id) {
        productServices.deleteProductByproductId(product_id);
        return "Product Deleted Sucessfully";
    }
}



