package com.olx.service;

import java.util.List;


import com.olx.dto.Users;

public interface LoginService {
	
	public String authenticate(Users users);
	public boolean logout(String authToken);
	public Users registerUser(Users users);
	public Users getUserDetail(String authToken);
	public List<Users> getAllUsers(String authToken);
	public boolean validate(String authToken);
	public Users getUserById(String authToken, int id);
	public Users getUserByUsername(String authToken);
	
	
}
