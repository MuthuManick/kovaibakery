package com.business.kovaibakery.restapis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.kovaibakery.entity.OrderEntity;
import com.business.kovaibakery.entity.ProductEntity;
import com.business.kovaibakery.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired OrderRepository orderRepository;
	
	@GetMapping()
	public List<OrderEntity> getAllOrders() {

		return orderRepository.findAll();
	}
	

	@PostMapping
	public ResponseEntity<ProductEntity> createOrder(@RequestBody OrderEntity orderEntity) {
		try {
			
			//OrderEntity modelOrderEntity = new OrderEntity();
			
			orderEntity.setDate(new Date());
			 
			return new ResponseEntity( orderRepository.save(orderEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
