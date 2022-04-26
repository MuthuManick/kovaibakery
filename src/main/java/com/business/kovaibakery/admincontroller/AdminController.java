package com.business.kovaibakery.admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.business.kovaibakery.admincontroller.exception.RecordNotFoundException;
import com.business.kovaibakery.entity.ProductEntity;
import com.business.kovaibakery.repository.ProductRepository;



@Controller
@RequestMapping("/admin-ui")
public class AdminController {
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping
	public String getAllProducts(Model model) 
	{
		List<ProductEntity> list = productRepository.findAll();

		model.addAttribute("products", list);
		return "list-products";
	}
	
	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			ProductEntity entity = productRepository.getById(id.get());
			model.addAttribute("product", entity);
		} else {
			model.addAttribute("product", new ProductEntity());
		}
		return "add-edit-product";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		productRepository.deleteById(id);
		return "redirect:/admin-ui/";
	}

	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	public String createOrUpdateProduct(ProductEntity product) {
		
		Optional<ProductEntity> receivedProduct = productRepository.findById(product.getId());
		if (receivedProduct.isPresent()) {
			System.out.println("Update process");

			ProductEntity updateProduct = new ProductEntity();
			updateProduct.setName(product.getName());
			updateProduct.setCategory(product.getCategory());
			updateProduct.setDescription(product.getDescription());
			updateProduct.setPricePerUnit(product.getPricePerUnit());

			productRepository.save(updateProduct);
		}
		else {
			System.out.println("creation process");
			productRepository.save(product);
			
		}
		return "redirect:/admin-ui/";
	}

}
