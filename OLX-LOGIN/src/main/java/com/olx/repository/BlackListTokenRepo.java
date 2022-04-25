package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.olx.entity.BlackListedTokensDocument;

@Repository
public interface BlackListTokenRepo extends MongoRepository<BlackListedTokensDocument, Integer>{

	BlackListedTokensDocument findByAuthToken(String authToken);

}
