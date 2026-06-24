package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.modal.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
