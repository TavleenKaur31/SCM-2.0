package com.c.Repositires;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.c.Entities.Contact;
import com.c.Entities.User;

public interface ContactRepo  extends JpaRepository<Contact, String>{
	
	
	//find the contacts by user
	
	
	public Page<Contact> findByUser(User user , Pageable pageable);
	
	
	@Query("SELECT c FROM Contact c WHERE c.user.id= :userid ")
	public	List<Contact> findByUserId(@Param("userid")String userid);
	

	
	   public Page<Contact> findByNameContainingAndUser(String nameKeyword, User user, Pageable pageable);

	    public Page<Contact> findByEmailContainingAndUser(String emailKeyword, User user, Pageable pageable);

	    public Page<Contact> findByPhoneNumberContainingAndUser(String phoneKeyword, User user, Pageable pageable);
}