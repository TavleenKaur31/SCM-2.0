package com.c.Services;

import java.util.List;
import java.util.UUID;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.c.Entities.Contact;
import com.c.Entities.User;
import com.c.Repositires.ContactRepo;
import com.c.helper.ResourceNotFoundException;




@Service
public class ContactSericeImpl implements ContactService {

	@Autowired
	private ContactRepo repo;
	
	
	@Override
	public Contact saveContact(Contact contact) {
		
		String contactid= UUID.randomUUID().toString();
		contact.setId(contactid);
		
		return repo.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		
		Contact contactOld = repo.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found"));
		  contactOld.setName(contact.getName());
	        contactOld.setEmail(contact.getEmail());
	        contactOld.setPhoneNumber(contact.getPhoneNumber());
	        contactOld.setAddress(contact.getAddress());
	        contactOld.setDescription(contact.getDescription());
	        contactOld.setPicture(contact.getPicture());
	        contactOld.setFavourite(contact.isFavourite());
	        contactOld.setWebsiteLink(contact.getWebsiteLink());
	        contactOld.setLinedInLink(contact.getLinedInLink());
	        contactOld.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
		
		return repo.save(contactOld);
	}

	@Override
	public List<Contact> getAllContact() {
		
		return repo.findAll();
	}

	@Override
	public Contact getById(String id) {
		
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found with given id : " +id));
	}

	@Override
	public void deleteContact(String id) {
		var c = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not found with given id : " +id));
		
		repo.delete(c);
		
	}

	

	@Override
	public List<Contact> getUserById(String userid) {
		List<Contact> findByUserId = repo.findByUserId(userid);
		return findByUserId;
	}

	  @Override
	    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {

	        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

	        var pageable = PageRequest.of(page, size, sort);

	        return repo.findByUser(user, pageable);

	    }

	@Override
	public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order ,User user) {
		
		   Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	        var pageable = PageRequest.of(page, size, sort);
	
		return repo.findByNameContainingAndUser(nameKeyword, user, pageable);
	}

	@Override
	public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User user) {
		 Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	        var pageable = PageRequest.of(page, size, sort);
		return repo.findByEmailContainingAndUser(emailKeyword, user,pageable );
	}

	



	


}
