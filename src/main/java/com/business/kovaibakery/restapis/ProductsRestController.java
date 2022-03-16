package com.business.kovaibakery.restapis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.kovaibakery.entity.ProductEntity;
import com.business.kovaibakery.repository.ProductRepository;

@RestController
public class ProductsRestController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<ProductEntity> getAllComments() {
		
		return productRepository.findAll();
	}

}
