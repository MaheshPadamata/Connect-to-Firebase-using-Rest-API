package com.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.object.Person;
import com.service.FirebaseService;
import com.service.sendSMS;


@ComponentScan(value="com.service")
@RestController
public class DemoController {

	@Autowired
	FirebaseService firebaseService;
	sendSMS sms;
	
	@RequestMapping(value="/")
	public String  display() {
		return "Connect to FireBase..!";
	}
	
	@RequestMapping("helloworld")
	public ModelAndView getdata(@RequestHeader String name) throws InterruptedException,ExecutionException{
		Person person=firebaseService.getUserDetails(name);
		System.out.println("Person:"+person);
		return new ModelAndView("hello", "message", person);
		//return firebaseService.getUserDetails(name);
	}
	
	@RequestMapping("/userdetails")
	public Person getuserdata(@RequestHeader String name) throws InterruptedException,ExecutionException{
		//Person person=firebaseService.getUserDetails(name);
		//System.out.println("Person:"+person);
		return firebaseService.getUserDetails(name);
	}
	
	@RequestMapping("/Entiredetails")
	public List<Person> getalluserdata() throws InterruptedException,ExecutionException{
		//Person person=firebaseService.getUserDetails(name);
		//System.out.println("Person:"+person);
		return firebaseService.getAllDetails();
	}
	
	@RequestMapping("/smsPhonenumbers")
	public List<Long> getphonenumbers() throws InterruptedException,ExecutionException{
		//Person person=firebaseService.getUserDetails(name);
		//System.out.println("Person:"+person);
		return firebaseService.getAllphoneNumberDetails();
	}
	
	//get All users data
	/*@RequestMapping("/getAllUserDetails")
	public List<Person> getalldata() throws InterruptedException,ExecutionException{
		return firebaseService.getUserAllDetails();
	}*/
	/*
	@PostMapping("/createUser")
	public String postdata(Person person) throws InterruptedException,ExecutionException{
		return firebaseService.saveUserDetails(person);
	}
	
*/	
}
