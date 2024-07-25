package com.c.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Builder
public class User implements UserDetails{

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", profilePic=" + profilePic + ", phoneNumber=" + phoneNumber + ", enabled=" + enabled
				+ ", emailverified=" + emailverified + ", phoneverified=" + phoneverified + ", provider=" + provider
				+ ", providerUserId=" + providerUserId + ", contact=" + contact + "]";
	}

	public User(String userId, String name, String email, String password, String about, String profilePic,
			String phoneNumber, boolean enabled, boolean emailverified, boolean phoneverified, Providers provider,
			String providerUserId, List<Contact> contact) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.profilePic = profilePic;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.emailverified = emailverified;
		this.phoneverified = phoneverified;
		this.provider = provider;
		this.providerUserId = providerUserId;
		this.contact = contact;
	}

	@Id
	private String userId;
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	@Column(length = 1000)
	private String about;
	@Column(length = 1000)
	private String profilePic;
	private String phoneNumber;
	private boolean enabled = true;
	private boolean emailverified = false;
	private boolean phoneverified = false;

	@Enumerated(value = EnumType.STRING)
	private Providers provider = Providers.SELF;
	private String providerUserId;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Contact> contact = new ArrayList<>();
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> rollList = new ArrayList<>();
	public String getUserId() {
		return userId;
	}

	public List<String> getRollList() {
		return rollList;
	}

	public void setRollList(List<String> rollList) {
		this.rollList = rollList;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEmailverified() {
		return emailverified;
	}

	public void setEmailverified(boolean emailverified) {
		this.emailverified = emailverified;
	}

	public boolean isPhoneverified() {
		return phoneverified;
	}

	public void setPhoneverified(boolean phoneverified) {
		this.phoneverified = phoneverified;
	}

	public Providers getProvider() {
		return provider;
	}

	public void setProvider(Providers provider) {
		this.provider = provider;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	/* ---------------------------------  */

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection <SimpleGrantedAuthority> collect = rollList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}


}
