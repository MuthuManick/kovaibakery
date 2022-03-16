package com.business.kovaibakery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priceperunit")
	private int pricePerUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	public ProductEntity()
	{
		
	}

	public ProductEntity(long id, String name, String category, String description, int pricePerUnit) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.pricePerUnit = pricePerUnit;
	}
	
	
	
	
	
	
	

}
