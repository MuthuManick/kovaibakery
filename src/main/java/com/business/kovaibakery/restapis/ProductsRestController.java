package com.business.kovaibakery.restapis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") Long Id) {

		Optional<ProductEntity> productEntity = productRepository.findById(Id);
		if (productEntity.isPresent()) {
			return new ResponseEntity<>(productEntity.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/products")

	public ResponseEntity<ProductEntity> createTutorial(@RequestBody ProductEntity productEntity) {

		try {
			ProductEntity productEntity1 = productRepository.save(productEntity);
			return new ResponseEntity<>(productEntity1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	 @PutMapping("/products/{id}")
	 public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") long id, @RequestBody ProductEntity productEntity) {
		    Optional<ProductEntity> productData = productRepository.findById(id);
		    if (productData.isPresent()) {
		    	ProductEntity productEntity1 = productData.get();
		    	productEntity1.setCategory(productEntity.getCategory());
		    	productEntity1.setDescription(productEntity.getDescription());
		    	productEntity1.setName(productEntity.getName());
		    	productEntity1.setPricePerUnit(productEntity.getPricePerUnit());
		    	
		    	return new ResponseEntity<>(productRepository.save(productEntity1), HttpStatus.OK);
		    }
		    else{
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	 }
	 
	 @DeleteMapping("/products/{id}")
	  public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") long id) {
	    try {
	    	productRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
