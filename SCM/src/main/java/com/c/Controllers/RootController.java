package com.c.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.c.Entities.User;
import com.c.Services.UserService;
import com.c.helper.Helper;

@ControllerAdvice
public class RootController {

	Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@Autowired
	private UserService service;
	
	
	@ModelAttribute
	public void addLoggedInUserInformation(Model model , Authentication autentication) {
		
		if (autentication == null) {
	        model.addAttribute("loggedinUser", null);
	        return;
	    }
		System.out.println("adding loged in user to user");
		String user = Helper.getEmailofLoggedinUser(autentication );
		logger.info("User logged in : {}", user);
		
		
		User userByEmail = service.getUserByEmail(user);
		//getting users form db
		
		if(userByEmail==null) {
		model.addAttribute("loggedinUser" , null);	
		}
		else {
			System.out.println(userByEmail.getName());
			System.out.println(userByEmail.getEmail());
			model.addAttribute("loggedinUser" ,userByEmail);
		}
		
		
	}
	
	
}
