package com.c.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {

	@NotBlank
	@Size(min = 3 ,message = "Min 3 Characters required")
	private String name;
	@Email( message ="Invalid Email Format!!")
	@NotBlank( message ="Email Required")
	private String email;
	@NotBlank(message = "paassword is required")
	@Size(min = 3 , message = "Min 3 characters required")
	private String password;
	@Size(min=8 , max=10 , message = "Invalid Phone number")
	private String phoneNumber;
	@NotBlank( message ="About is required")
	private String about;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", about=" + about + "]";
	}
	
	
	
}
