package com.c.Controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.c.Entities.User;
import com.c.Services.UserService;
import com.c.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	//user dashboard page
	
	
	
	
	@RequestMapping("/dashboard")
	  public String dashboard() {
		
		return "user/dashboard";
	}
	
	
	
	@RequestMapping("/profile")
	  public void profile(Model model , Authentication authentication) {

		if(authentication==null) {
			return;
		}

		String user = Helper.getEmailofLoggedinUser(authentication );
		logger.info("User logged in : {}", user);
		
		
		User userByEmail = service.getUserByEmail(user);
		//getting users form db
		System.out.println(userByEmail.getName());
		System.out.println(userByEmail.getEmail());
		model.addAttribute("loggedinUser" ,userByEmail);
		
		
		
	}
	
	
	
	//usr add contact page
}
