package com.velocity.m1.service;

import com.velocity.m1.model.ProductRequest;
import com.velocity.m1.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
