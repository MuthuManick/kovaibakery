package com.business.kovaibakery.admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.business.kovaibakery.admincontroller.exception.RecordNotFoundException;
import com.business.kovaibakery.entity.AdminUsersEntity;
import com.business.kovaibakery.entity.ProductEntity;
import com.business.kovaibakery.exception.UserNotLoggedIn;
import com.business.kovaibakery.model.LoginModel;
import com.business.kovaibakery.repository.AdminUsersRepository;
import com.business.kovaibakery.repository.ProductRepository;

@Controller
@RequestMapping("/admin-ui")
public class AdminController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	AdminUsersRepository adminUsersRepository;
	
	boolean isLoggedIn = false;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginModel());
		return "/login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("login", new LoginModel());
		isLoggedIn = false;
		return "redirect:/admin-ui/login";
	}

	@RequestMapping(path = "/loginsubmit", method = RequestMethod.POST)
	public String loginSubmit(@ModelAttribute("login") LoginModel login) {

		System.out.println("The input model is " + login.getUserName());
		System.out.println("The input model is " + login.getPassword());

		Optional<AdminUsersEntity> adminUser = adminUsersRepository.findByUserName(login.getUserName().trim());
		if (adminUser.isPresent()) {
			System.out.println("The result username is " + adminUser.get().getUserName());
			System.out.println("The result password is " + adminUser.get().getPassword());
			
			if(login.getUserName().equalsIgnoreCase(adminUser.get().getUserName()) && login.getPassword().equalsIgnoreCase(adminUser.get().getPassword()))
			{
				isLoggedIn = true;
			}
		}

		return "redirect:/admin-ui/";
	}

	@RequestMapping
	public String getAllProducts(Model model) throws Exception {
		List<ProductEntity> list = productRepository.findAll();

		model.addAttribute("products", list);
		
		if(!isLoggedIn) {
			//throw new UserNotLoggedIn();
			return "Error";
		}
		return "list-products";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			ProductEntity entity = productRepository.getById(id.get());
			model.addAttribute("product", entity);
		} else {
			model.addAttribute("product", new ProductEntity());
		}
		if(!isLoggedIn)
		{
			return "Error";
		}
		return "add-edit-product";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		if(!isLoggedIn)
		{
			return "Error";
		}
		productRepository.deleteById(id);
		return "redirect:/admin-ui/";
	}

	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	public String createOrUpdateProduct(ProductEntity product) {
		
		if(!isLoggedIn)
		{
			return "Error";
		}

		Optional<ProductEntity> receivedProduct = productRepository.findById(product.getId());
		if (receivedProduct.isPresent()) {
			System.out.println("Update process");

			ProductEntity updateProduct = new ProductEntity();
			updateProduct.setId(receivedProduct.get().getId());
			updateProduct.setName(product.getName());
			updateProduct.setCategory(product.getCategory());
			updateProduct.setDescription(product.getDescription());
			updateProduct.setPricePerUnit(product.getPricePerUnit());

			productRepository.save(updateProduct);
		} else {
			System.out.println("creation process");
			productRepository.save(product);

		}
		
		return "redirect:/admin-ui/";
	}

}
