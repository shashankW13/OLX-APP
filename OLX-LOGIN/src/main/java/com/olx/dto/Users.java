package com.olx.dto;

import lombok.Data;

@Data
public class Users {
	
	private int id;
	private String username;
	private String password;
	private String roles;
	private boolean active;
	private String firstname;
	private String lastname;
	private String email;
	private long phone;
	
}
