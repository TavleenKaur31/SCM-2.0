package com.c.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {

	@Id
	private String id;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String picture;
	@Column(length = 1000)
	private String description;
	private boolean favourite=false;
	private String websiteLink;
	private String linedInLink;
	//private List<String> socialLink = new ArrayList<>();
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public String getCloudinaryImagePublicId() {
		return cloudinaryImagePublicId;
	}

	public void setCloudinaryImagePublicId(String cloudinaryImagePublicId) {
		this.cloudinaryImagePublicId = cloudinaryImagePublicId;
	}

	private String cloudinaryImagePublicId;
	
	@OneToMany(mappedBy = "contact" , cascade = CascadeType.ALL , fetch = FetchType.EAGER , orphanRemoval = true)
	private List<SocialLink> links= new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getLinedInLink() {
		return linedInLink;
	}

	public void setLinedInLink(String linedInLink) {
		this.linedInLink = linedInLink;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SocialLink> getLinks() {
		return links;
	}

	public void setLinks(List<SocialLink> links) {
		this.links = links;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String id, String name, String email, String phoneNumber, String address, String picture,
			String description, boolean favourite, String websiteLink, String linedInLink, User user,
			String cloudinaryImagePublicId, List<SocialLink> links) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.picture = picture;
		this.description = description;
		this.favourite = favourite;
		this.websiteLink = websiteLink;
		this.linedInLink = linedInLink;
		this.user = user;
		this.cloudinaryImagePublicId = cloudinaryImagePublicId;
		this.links = links;
	}


    		
	
}
