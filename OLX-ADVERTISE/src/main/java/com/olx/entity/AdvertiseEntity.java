package com.olx.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advertises")
public class AdvertiseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	private String description;
	private double price;
	
	@Column(name="category_name")
	private String categoryName;
	private int category;
	
	private int status;
	@Column(name="status_Name")
	private String statusName;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	private boolean active;
	private String username;
}
