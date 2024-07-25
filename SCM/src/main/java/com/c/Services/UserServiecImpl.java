package com.c.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.c.Entities.User;
import com.c.Repositires.UserRepo;
import com.c.helper.AppConstants;
import com.c.helper.ResourceNotFoundException;


@Service
public class UserServiecImpl implements UserService{

	@Autowired
	private UserRepo repo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRollList(List.of(AppConstants.ROLE_USER));
		  User save = repo.save(user);
		return save;
	}

	@Override 
	public Optional<User> getUserById(String id) {
		Optional<User> findById = repo.findById(id);
		return findById;
	}

	@Override
	public User updateUser(User user) {
		User userr = repo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		
		userr.setName(user.getName());
		userr.setEmail(user.getEmail());
		userr.setPassword(user.getPassword());
		userr.setAbout(user.getAbout());
		userr.setPhoneNumber(user.getPhoneNumber());
		userr.setProfilePic(user.getProfilePic());
		userr.setEnabled(user.isEnabled());
		userr.setEmailverified(user.isEmailverified());
		userr.setPhoneverified(user.isPhoneverified());
		userr.setProvider(user.getProvider());
		userr.setProviderUserId(user.getProviderUserId());
		
		User save = repo.save(userr);
		return save;
	}

	@Override
	public void deleteUser(String id) {
		User userr = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
	 repo.delete(userr);
	}

	@Override
	public boolean isUserExist(String userId) {
		User userr = repo.findById(userId).orElse(null);
		return userr != null ? true : false;
	}

	@Override
	public boolean isuserExistByEmail(String email) {
		User orElse = repo.findByEmail(email).orElse(null); 
		return orElse != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		
		return repo.findByEmail(email).orElse(null);
	}

}
