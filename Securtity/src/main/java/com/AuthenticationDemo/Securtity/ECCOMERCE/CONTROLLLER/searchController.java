package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.categoryServices;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.productServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@AllArgsConstructor
public class searchController {

    private  final productServices productServices;


    private final categoryServices categoryServices;

    @GetMapping("/api/v3/search")
    public Map<String, Object> search(@RequestParam String q) {
        List<Product> products = productServices.searchProducts(q);
        List<Category> categories = categoryServices.searchCategories(q);

        Map<String, Object> results = new HashMap<>();
        results.put("products", products);
        results.put("categories", categories);

        return results;
    }
}
