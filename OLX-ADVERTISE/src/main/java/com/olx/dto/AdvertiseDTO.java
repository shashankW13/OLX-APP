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
}
