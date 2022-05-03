package com.business.kovaibakery.restapis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.kovaibakery.entity.AdminUsersEntity;
import com.business.kovaibakery.repository.AdminUsersRepository;

@RestController("/admin/users")
public class AdminUsers {
	
	@Autowired
	AdminUsersRepository adminUsersRepository;
	 
	@GetMapping("/get")
	public List<AdminUsersEntity> getUsers(){
		
		
		return adminUsersRepository.findAll();
		
		
	}

}
