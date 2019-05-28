/**
 * 
 */
package com.poc.springk8s.repository;

//import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.springk8s.model.Person;

/**
 * @author GANESH
 *
 
public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByUuid(String uuid);
}
*/

public interface PersonRepository{
	
}