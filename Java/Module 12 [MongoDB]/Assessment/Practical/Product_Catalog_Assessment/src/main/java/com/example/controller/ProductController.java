package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.modal.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/dbname")
    public String getDbName() {
        return mongoTemplate.getDb().getName();
    }
    
//    get database name 
//    @GetMapping("/allinfo")
//    public String allInfo() {
//        return "DB = " + mongoTemplate.getDb().getName();
//    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }

    @PutMapping("/{id}/description")
    public String updateDescription(
            @PathVariable String id,
            @RequestParam String description) {

        service.updateDescription(id, description);
        return "Description Updated";
    }

    @PutMapping("/{id}/stock")
    public String updateStock(
            @PathVariable String id,
            @RequestParam int quantity) {

        service.updateStock(id, quantity);
        return "Stock Updated";
    }
}