package com.velocity.m1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velocity.m1.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
