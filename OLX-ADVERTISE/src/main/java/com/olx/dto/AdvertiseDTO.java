package com.olx.dto;

import java.time.LocalDate;


import lombok.Data;

@Data
public class AdvertiseDTO {
	
	private int id;
	private String title;
	private String description;
	private double price;
	private int category;
	private String categoryName;
	private int status;
	private String statusName;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private boolean active;
	private String username;
	
	public AdvertiseDTO(int id, String title, String description, double price, int category, String categoryName,
			int status, String statusName, LocalDate createdDate, LocalDate modifiedDate, boolean active,
			String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.categoryName = categoryName;
		this.status = status;
		this.statusName = statusName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.username = username;
	}
	
	public AdvertiseDTO() {
		
	}
	
	
}
