package com.c.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c.Entities.Contact;
import com.c.Services.ContactService;


@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ContactService service;
	
	
	@GetMapping("/contacts/{contactid}")
	public Contact getContact(@PathVariable String contactid ) {
		return this.service.getById(contactid);
		
	}
	
	
	
		

}
