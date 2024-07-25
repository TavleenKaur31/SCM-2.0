package com.c.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.c.Entities.Contact;
import com.c.Entities.User;

public interface ContactService {
	
	

	public Contact saveContact(Contact contact);
	public Contact updateContact(Contact contact);
	
	public List<Contact> getAllContact();
	
	public Contact getById(String id);
	
	public void deleteContact(String id);
	
	public  Page<Contact> searchByName(String nameKeyword ,int size ,int page,String sortBy , String order, User user);
	public  Page<Contact> searchByEmail(String emailKeyword ,int size ,int page,String sortBy , String order ,User user);
	
	
	public List<Contact> getUserById(String userid);
	
	public Page<Contact> getByUser(User user , int page , int size ,String sortBy, String direction);

	
	
	
	
}
