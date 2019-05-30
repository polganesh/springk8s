/**
 * 
 */
package com.poc.springk8s.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.springk8s.model.Person;
import com.poc.springk8s.service.PersonService;


/**
 * @author GANESH
 *
 */

@RestController
public class PeopleController {
  Logger logger = LoggerFactory.getLogger(PeopleController.class);

	
	@Autowired
	PersonService personService;

	/**
	 * 
	 */
	public PeopleController() {
	}
	
	@PostMapping("/people")
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		logger.info("person to be saved "+person);
		personService.save(person);
		
        return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
//	@GetMapping("/people")
//	public List<Person> findAll() {
//		return personService.findAll();
//	}
	
	@GetMapping("/people")
	public List<Person> findAll() {
		List<Person> result=personService.findAll();
		logger.info("find all results "+result);
		return result;
	}
	
	

}
