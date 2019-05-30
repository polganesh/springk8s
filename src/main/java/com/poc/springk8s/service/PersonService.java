package com.poc.springk8s.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.springk8s.model.Person;
import com.poc.springk8s.repository.PersonRepository;

//import reactor.core.publisher.Flux;

@Service
public class PersonService {
	
	Logger logger = LoggerFactory.getLogger(PersonService.class);
	
	private static Map<String, Person> personData=new HashMap<String,Person>();

	@Autowired
	PersonRepository personRepository;
	
	public PersonService() {
		// TODO Auto-generated constructor stub
	}
	
	// function to generate a random string of length n 
    static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
    } 
	
	
	
	public void save(Person person) {
		//personRepository.save(person);
		personRepository.insert(person);
		logger.info("after persisting person  "+person);

//		String id=getAlphaNumericString(15);
//		person.setId(id);
//		personData.put(id, person);
		
		
	}
	
//	public Flux<Person> findAll(){
//		Flux<Person> result=personRepository.findAll();
//		logger.info("result for findAll  "+result);
//		return result;
//		//return personData.values();
//	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	

}
