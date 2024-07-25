package com.c.forms;

import org.springframework.web.multipart.MultipartFile;

import com.c.validators.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Contactform {
	
	
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email Is required")
	@Email(message = "Invalid Email Address")
	private String email;
	@NotBlank(message = "PhoneNumber is required!")
	@Pattern(regexp = "^[0-9]{10}$" , message = "Inavlid Phone Number")
	private String phoneNumber;
	@NotBlank( message = "Address is required")
	private String address;
	private String description;
	private boolean favourite;
	private String websiteLink;
	private String linkedinLink;
	@ValidFile
	private MultipartFile profileimage;
	
	private String picture;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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
	public String getLinkedinLink() {
		return linkedinLink;
	}
	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}
	public MultipartFile getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(MultipartFile profileimage) {
		this.profileimage = profileimage;
	}
	public Contactform() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contactform(String name, String email, String password, String phoneNumber, String address,
			String description, boolean favourite, String websiteLink, String linkedinLink,
			MultipartFile profileimage) {
		super();
		this.name = name;
		this.email = email;
		
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.description = description;
		this.favourite = favourite;
		this.websiteLink = websiteLink;
		this.linkedinLink = linkedinLink;
		this.profileimage = profileimage;
	}
	@Override
	public String toString() {
		return "Contactform [name=" + name + ", email=" + email  + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", description=" + description + ", favourite=" + favourite
				+ ", websiteLink=" + websiteLink + ", linkedinLink=" + linkedinLink + ", profileimage=" + profileimage
				+ "]";
	}
	
	
	
	

}
