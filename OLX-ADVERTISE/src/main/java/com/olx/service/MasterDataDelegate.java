package com.olx.service;


public interface MasterDataDelegate {
	
	public String getCategoryDescription(int categoryId,  String authToken);
	public String getStatusName(int statusId,  String authToken);
}