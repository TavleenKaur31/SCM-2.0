package com.c.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.c.Entities.User;
import com.c.Services.UserService;
import com.c.forms.UserForm;
import com.c.helper.Message;
import com.c.helper.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Pagecontroller {

	@Autowired
	private UserService service;
	
	@RequestMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("name", "Tavleen" );
		return "home";
		
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/home ";
	}
	
	
	@RequestMapping("/about")
	public String about() {
		return "about";
		
	}
	
	@RequestMapping("/service")
	public String service() {
		return "services";
		
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
		
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
		
	}
	@RequestMapping("/register")
	public String signup(Model model) {
		UserForm userForm = new UserForm();
		
		model.addAttribute("userForm",userForm);
		return "signup";
		
	}
	
	
	@PostMapping("/do-register")
	public String processRegister(@Valid @ModelAttribute UserForm userform ,BindingResult bindingresult , HttpSession session) {
		System.out.println("processing..");
		System.out.println(userform);
		
		//validatte--->
		
		if(bindingresult.hasErrors()) {
			return "signup";
		}
		
		  User user = new User();
		    user.setName(userform.getName());
		    user.setEmail(userform.getEmail());
		    user.setAbout(userform.getAbout());
		    user.setPhoneNumber(userform.getPhoneNumber());
		    user.setPassword(userform.getPassword());
		    user.setProfilePic("https://static.vecteezy.com/system/resources/thumbnails/009/209/212/small/neon-glowing-profile-icon-3d-illustration-vector.jpg");
		    
		 Message message = new Message();
		 message.setContent("Registration Successful");
		 message.setType(MessageType.green);
		
		service.saveUser(user);
		System.out.println("user saved..");
		session.setAttribute("message", message);
		return "redirect:/register";
		
	}
}
