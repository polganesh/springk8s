/**
 * 
 */
package com.poc.springk8s.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.springk8s.model.Person;

/**
 * @author GANESH
 *
*/


//@Repository
//public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
//
//	
//	//Person findById(String uuid);
//}

@Repository
public interface PersonRepository extends MongoRepository<Person, Long>{
	
}