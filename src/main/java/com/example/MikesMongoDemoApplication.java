package com.example;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MikesMongoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikesMongoDemoApplication.class, args);
	}
}

@RestController
class PersonRestController {
	@RequestMapping("/people")
	Collection<Person> people() {
		return this.pr.findAll();
	}
	
	@Autowired
	PersonRepo pr;
}

@Component
class PersonCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	  pr.deleteAll();
	  
	  pr.save(new Person("George","Washington"));
	  pr.save(new Person("Thomas","Jefferson"));
	  pr.save(new Person("Abraham","Lincoln"));
	  
	  for (Person p: this.pr.findAll()) {
		 System.out.println(p.toString());  
	  }
	}
	
	@Autowired
	PersonRepo pr;
}

interface PersonRepo extends MongoRepository<Person, String> {
	Collection<Person> findByLastName(String lastName);
}

class Person {
	
	@Id
	String id;
	
	String firstName;
	String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}