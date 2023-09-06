package com.velocity.m1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.velocity.m1.model.ProductRequest;
import com.velocity.m1.model.ProductResponse;
import com.velocity.m1.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/add")
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
		long productId = service.addProduct(productRequest);
		return new ResponseEntity<Long>(productId, HttpStatus.CREATED);

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId) {
		ProductResponse productResponse = service.getProductById(productId);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}

	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity) {
		service.reduceQuantity(productId, quantity);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
