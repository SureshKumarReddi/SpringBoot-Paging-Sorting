package com.Suresh.SpringBoot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Suresh.SpringBoot.dto.APIResponse;
import com.Suresh.SpringBoot.entity.Product;
import com.Suresh.SpringBoot.repository.ProductRepository;
import com.Suresh.SpringBoot.service.ProductService;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class SpringBootPagingSortingApplication {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPagingSortingApplication.class, args);
	}

	/*
	 * @PostConstruct public void initDb() { List<Product> collect =
	 * IntStream.rangeClosed(1, 200) .mapToObj(value -> new
	 * Product("product "+value, new Random().nextInt(100), new
	 * Random().nextInt(5000))) .collect(Collectors.toList());
	 * repository.saveAll(collect); }
	 */

	// retrieve all users
	@RequestMapping(value = "/all", produces = "application/json")
	public APIResponse<List<Product>> getAll() {
		List<Product> all = service.retrieveAll();
		System.out.println(all);
		return new APIResponse<>(all.size(), all);

	}

	@GetMapping("/{field}")
	private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field) {
		List<Product> allProducts = service.findProductsWithSorting(field);
		return new APIResponse<>(allProducts.size(), allProducts);
	}

	@GetMapping("/pagination/{offset}/{pageSize}")
	private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
		Page<Product> productsWithPagination = service.findProductsWithPagination(offset, pageSize);
		return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
	}

	@GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		Page<Product> productsWithPagination = service.findProductsWithPaginationAndSorting(offset, pageSize, field);
		return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
	}

}
