package com.c.Controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.c.Entities.Contact;
import com.c.Entities.User;
import com.c.Services.ContactService;
import com.c.Services.ImageService;
import com.c.Services.UserService;
import com.c.forms.ContactSearchForm;
import com.c.forms.Contactform;
import com.c.helper.AppConstants;
import com.c.helper.Helper;
import com.c.helper.Message;
import com.c.helper.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

	private ContactService service;

	@Autowired
	private UserService userservice;

	@Autowired
	private Helper helper;

	@Autowired
	private ImageService imageservice;

	@Autowired
	public ContactController(ContactService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/add")
	public String addContact(Model model) {

		Contactform contactForm = new Contactform();
		System.out.println();
		model.addAttribute("contactForm", contactForm);
		return "user/add_contact";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveContact(@Valid @ModelAttribute("contactForm") Contactform contactForm, BindingResult result,
			Authentication authentication, HttpSession session, Model model) {

		if (result.hasErrors()) {
			System.out.println("Validation errors found");
			result.getAllErrors().forEach(error -> {
				System.out.println(error.getDefaultMessage());
			});
			model.addAttribute("contactForm", contactForm); // Ensure contactForm is added to the model
			return "user/add_contact";
		}

		String username = helper.getEmailofLoggedinUser(authentication);

		User user = userservice.getUserByEmail(username);

		// process the contact picture

		// image process

		String filename = UUID.randomUUID().toString();
		String imageUrl = null;
		if (contactForm.getProfileimage() != null && !contactForm.getProfileimage().isEmpty()) {
			try {
				imageUrl = imageservice.uploadimage(contactForm.getProfileimage(), filename);
				logger.info("Uploaded image URL: {}", imageUrl);
			} catch (Exception e) {
				logger.error("Failed to upload image: {}", e.getMessage());
				// Handle image upload error
			}
		}

		Contact contact = new Contact();
		contact.setName(contactForm.getName());
		contact.setFavourite(contactForm.isFavourite());
		contact.setEmail(contactForm.getEmail());
		contact.setPhoneNumber(contactForm.getPhoneNumber());
		contact.setAddress(contactForm.getAddress());
		contact.setDescription(contactForm.getDescription());
		contact.setUser(user);
		contact.setLinedInLink(contactForm.getLinkedinLink());
		contact.setWebsiteLink(contactForm.getWebsiteLink());
		contact.setPicture(imageUrl);
		contact.setCloudinaryImagePublicId(filename);

		service.saveContact(contact);

		System.out.println(contactForm);
		Message message = new Message();
		message.setContent("Registration Successful");
		message.setType(MessageType.green);

		session.setAttribute("message", message);
		return "redirect:/user/contacts/add";
	}

	
	
	
	@RequestMapping
	public String viewContact( 
			  @RequestParam(value = "page", defaultValue = "0") int page,
	            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
	            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
	            @RequestParam(value = "direction", defaultValue = "asc") String direction
			
			
			
			,Authentication authentication, Model model) {
		String user = Helper.getEmailofLoggedinUser(authentication);
		
		User userByEmail = userservice.getUserByEmail(user);
		
		Page<Contact> pagecontacts = service.getByUser(userByEmail,page,size,sortBy,direction);
		
		  model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
		  
		
		model.addAttribute("pagecontacts",pagecontacts);
		model.addAttribute("contactSearchForm" , new ContactSearchForm());
		return "user/contacts";

	}
	
	
	@RequestMapping("/search")
	public String  searchHandler(  @ModelAttribute ContactSearchForm contactSearchForm,@RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction ,Model model, Authentication authentication) {
		
		logger.info("field {} keyword {} ",contactSearchForm.getField(),contactSearchForm.getValue());
		
		
		User user=userservice.getUserByEmail(helper.getEmailofLoggedinUser(authentication));
		
		Page<Contact> pagecontacts=null;
		if(contactSearchForm.getField().equalsIgnoreCase("name")) {
			pagecontacts= service.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction , user);
		}
		else if(contactSearchForm.getField().equalsIgnoreCase("email")){
			pagecontacts= service.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction, user);
		}
		
		model.addAttribute("contactSearchForm",contactSearchForm);
		
		model.addAttribute("pagecontacts" , pagecontacts);
		System.out.println(pagecontacts);
		model.addAttribute("pageSize" , AppConstants.PAGE_SIZE);
		
		
		return "user/search";
		
		
	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteContact(@PathVariable String id , HttpSession session) {
	
		
		service.deleteContact(id);
		Message message= new Message();
		message.setContent("Contact Deleted Successfully");
		message.setType(MessageType.green);
		
		session.setAttribute("message", message);
		return "redirect:/user/contacts";
		
	}
	
	
	//update contact for view
	 @GetMapping("/view/{contactId}")
	    public String updateContactFormView(
	            @PathVariable("contactId") String contactId,
	            Model model) {
		 
		 
		 
		    Contact contact = service.getById(contactId);
		    Contactform contactForm = new Contactform();
		    contactForm.setName(contact.getName());
	        contactForm.setEmail(contact.getEmail());
	        contactForm.setPhoneNumber(contact.getPhoneNumber());
	        contactForm.setAddress(contact.getAddress());
	        contactForm.setDescription(contact.getDescription());
	        contactForm.setFavourite(contact.isFavourite());
	        contactForm.setWebsiteLink(contact.getWebsiteLink());
	        contactForm.setLinkedinLink(contact.getLinedInLink());
	        contactForm.setPicture(contact.getPicture());
		 
		 model.addAttribute("contactForm", contactForm);
		 model.addAttribute("contactid", contactId);
					return "user/update_contact_view";
		 
		 
	 }
	
	 
	 @RequestMapping(value = "/update/{contactId}", method = RequestMethod.POST)
	    public String updateContact(@PathVariable("contactId") String contactId,
	            @Valid @ModelAttribute("contactForm") Contactform contactForm,
	            BindingResult bindingResult,
	            Model model, HttpSession session) {
		 
		 if (bindingResult.hasErrors()) {
	            return "user/update_contact_view";
	        }
		 
		 
		 var con = service.getById(contactId);
		 con.setId(contactId);
	        con.setName(contactForm.getName());
	        con.setEmail(contactForm.getEmail());
	        con.setPhoneNumber(contactForm.getPhoneNumber());
	        con.setAddress(contactForm.getAddress());
	        con.setDescription(contactForm.getDescription());
	        con.setFavourite(contactForm.isFavourite());
	        con.setWebsiteLink(contactForm.getWebsiteLink());
	        con.setLinedInLink(contactForm.getLinkedinLink());
	        
	        if (contactForm.getProfileimage() != null && !contactForm.getProfileimage().isEmpty()) {
	            logger.info("file is not empty");
	            String fileName = UUID.randomUUID().toString();
	            String imageUrl = imageservice.uploadimage(contactForm.getProfileimage(), fileName);
	            con.setCloudinaryImagePublicId(fileName);
	            con.setPicture(imageUrl);
	            contactForm.setPicture(imageUrl);

	        } else {
	            logger.info("file is empty");
	        }
	        
	        
	        Message message= new Message();
			message.setContent("Contact Updated Successfully");
			message.setType(MessageType.green);
			
			session.setAttribute("message", message);
		 
		 service.updateContact(con);
		 
		 
		 
					return "redirect:/user/contacts/view/" + contactId;

	 }
	 
	
}
