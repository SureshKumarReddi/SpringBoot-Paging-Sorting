package com.Suresh.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Suresh.SpringBoot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
