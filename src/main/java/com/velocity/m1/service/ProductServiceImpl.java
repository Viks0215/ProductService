package com.velocity.m1.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velocity.m1.entity.Product;
import com.velocity.m1.exception.ProductServiceException;
import com.velocity.m1.model.ProductRequest;
import com.velocity.m1.model.ProductResponse;
import com.velocity.m1.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		LOG.info("Adding product...");
		Product product = Product.builder().productName(productRequest.getName()).price(productRequest.getPrice())
				.quantity(productRequest.getQuantity()).build();
		productRepository.save(product);
		LOG.info("Product Added...");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		LOG.info("getting product for :: {}",productId);
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceException("Product with given id id not found","PRODUCT_NOT_FOUND"));
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		LOG.info("Product fetched...");
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		LOG.info("Reducing quantity {} for productId {}",quantity,productId);
		Product product = productRepository.findById(productId).orElseThrow(()->new ProductServiceException("Product with given Id is not found", "PRODUCT_NOT_FOUND"));
		
		if(product.getQuantity()<quantity) {
			throw new ProductServiceException("Product does not have sufficient quantity", "INSUFFICENT QUANTITY");
		}
		
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
		LOG.info("Product Quantity is updated successfully...");
	}

}
