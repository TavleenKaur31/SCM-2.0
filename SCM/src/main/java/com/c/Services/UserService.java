package com.c.Services;

import java.util.List;
import java.util.Optional;

import com.c.Entities.User;

public interface UserService {

	 
	public User saveUser(User user);
	public Optional<User> getUserById( String id);
	public User updateUser(User user);
	public void deleteUser(String id);
	public boolean isUserExist(String userId);
	public boolean isuserExistByEmail(String email);
	public List<User> getAllUsers();
	
	public  User getUserByEmail(String email);
	
	
	
}
