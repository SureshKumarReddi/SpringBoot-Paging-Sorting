package com.Suresh.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Suresh.SpringBoot.entity.Product;
import com.Suresh.SpringBoot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> retrieveAll() {
		return repository.findAll();
	}

	public List<Product> findProductsWithSorting(String field) {
		return repository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<Product> findProductsWithPagination(int offset, int pageSize) {
		Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
		return products;
	}

	public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
		Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return products;
	}
}