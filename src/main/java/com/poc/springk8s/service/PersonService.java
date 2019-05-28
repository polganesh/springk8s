package com.poc.springk8s.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.springk8s.model.Person;
import com.poc.springk8s.repository.PersonRepository;

@Service
public class PersonService {
	
	private static Map<String, Person> personData=new HashMap<String,Person>();

	@Autowired
	//PersonRepository personRepository;
	
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
		String id=getAlphaNumericString(15);
		person.setId(id);
		personData.put(id, person);
		
		
	}
	
	public Collection<Person> findAll(){
		//return personRepository.findAll();
		return personData.values();
	}
	
	

}
