package com.flightapp.main.service;

import java.util.List;
import java.util.Optional;

import com.flightapp.main.model.User;

public interface UserService {
	
	public Boolean findByUsername(String username);
	
	public Boolean findByEmail(String email);
	
	public User saveUser(User user);

	public Optional<User> findUser(String username);

	public List<User> fetchAllUsers();

}
