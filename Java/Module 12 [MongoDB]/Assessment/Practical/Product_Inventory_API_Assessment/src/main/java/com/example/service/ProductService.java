package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.dto.CategoryStockDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.modal.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public Product addProduct(Product product) {
		return repository.save(product);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product getProductById(String id) {

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}

	public Product updateProduct(String id, Product product) {

		Product existing = getProductById(id);

		existing.setName(product.getName());
		existing.setDescription(product.getDescription());
		existing.setPrice(product.getPrice());
		existing.setCategory(product.getCategory());
		existing.setStock(product.getStock());

		return repository.save(existing);
	}

	public void deleteProduct(String id) {

		Product product = getProductById(id);

		repository.delete(product);
	}

	public Product increaseStock(String id, int quantity) {

		Query query = new Query(Criteria.where("_id").is(id));

		Update update = new Update().inc("stock", quantity);

		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
				Product.class);
	}

	public Product decreaseStock(String id, int quantity) {

		Query query = new Query(Criteria.where("_id").is(id));

		Update update = new Update().inc("stock", -quantity);

		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
				Product.class);
	}

	public List<CategoryStockDTO> categorySummary() {

		Aggregation aggregation = Aggregation.newAggregation(

				Aggregation.group("category").sum("stock").as("totalStock").sum("price").as("totalValue"),

				Aggregation.project().and("_id").as("category").and("totalStock").as("totalStock").and("totalValue")
						.as("totalValue"));

		AggregationResults<CategoryStockDTO> results = mongoTemplate.aggregate(aggregation, "products",
				CategoryStockDTO.class);

		return results.getMappedResults();
	}
}