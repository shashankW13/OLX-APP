package com.olx.service;


public interface LoginServiceDelegate {

	public boolean isTokenValid(String authToken);

	Object getUsername(String authToken);
}
