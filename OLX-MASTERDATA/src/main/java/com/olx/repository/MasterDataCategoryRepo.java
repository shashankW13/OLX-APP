package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.MasterDataCategoryEntity;

public interface MasterDataCategoryRepo extends JpaRepository<MasterDataCategoryEntity, Integer>{

}