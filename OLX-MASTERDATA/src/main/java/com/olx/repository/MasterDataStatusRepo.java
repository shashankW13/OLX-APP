package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.MasterDataStatusEntity;

public interface MasterDataStatusRepo extends JpaRepository<MasterDataStatusEntity, Integer>{
	
}