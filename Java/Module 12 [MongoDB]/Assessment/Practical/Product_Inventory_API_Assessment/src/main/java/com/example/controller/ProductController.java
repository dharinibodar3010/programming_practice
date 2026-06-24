package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dto.CategoryStockDTO;
import com.example.modal.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable String id) {
		return service.getProductById(id);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable String id, @RequestBody Product product) {

		return service.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable String id) {

		service.deleteProduct(id);

		return "Deleted Successfully";
	}

	@PatchMapping("/{id}/restock")
	public Product restock(@PathVariable String id, @RequestParam int quantity) {

		return service.increaseStock(id, quantity);
	}

	@PatchMapping("/{id}/purchase")
	public Product purchase(@PathVariable String id, @RequestParam int quantity) {

		return service.decreaseStock(id, quantity);
	}

	@GetMapping("/category-summary")
	public List<CategoryStockDTO> categorySummary() {

		return service.categorySummary();
	}
}