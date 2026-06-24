package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.modal.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public void updateDescription(String id, String description) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .set("description", description);

        mongoTemplate.updateFirst(
                query,
                update,
                Product.class);
    }

    public void updateStock(String id, int quantity) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .inc("stock", quantity);

        mongoTemplate.updateFirst(
                query,
                update,
                Product.class);
    }
}
