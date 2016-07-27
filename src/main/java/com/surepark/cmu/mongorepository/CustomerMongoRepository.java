package com.surepark.cmu.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.surepark.cmu.domains.CustomerMongoModel;

public interface CustomerMongoRepository extends MongoRepository<CustomerMongoModel, String>{
	
	

}
