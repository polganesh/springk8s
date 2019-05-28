/**
 * 
 */
package com.poc.springk8s.controller;

import java.util.Collection;
import java.util.List;

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
	
	@Autowired
	PersonService personService;

	/**
	 * 
	 */
	public PeopleController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/people")
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		personService.save(person);
		
        return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@GetMapping("/people")
	public Collection<Person> findAll() {
		return personService.findAll();
	}

}
