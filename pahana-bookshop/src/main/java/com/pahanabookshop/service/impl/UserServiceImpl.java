package com.pahanabookshop.service.impl;

import java.util.Optional;

import com.pahanabookshop.dao.UserDao;
import com.pahanabookshop.model.User;
import com.pahanabookshop.service.UserService;

public class UserServiceImpl implements UserService {
	 private final UserDao userDao;

	    public UserServiceImpl(UserDao userDao) {
	        this.userDao = userDao;
	    }

	    @Override
	    public void addUser(User user) {
	        if (user == null) throw new IllegalArgumentException("User cannot be null");
	        if (user.getUsername() == null || user.getUsername().isEmpty()) {
	            throw new IllegalArgumentException("Username is required");
	        }
	        if (user.getPassword() == null || user.getPassword().isEmpty()) {
	            throw new IllegalArgumentException("Password is required");
	        }
	        if (user.getRole() == null || user.getRole().isEmpty()) {
	            throw new IllegalArgumentException("Role is required");
	        }
	        
	        if (!user.getRole().equalsIgnoreCase("cashier")) {
	            throw new IllegalArgumentException("Role must be 'cashier'");
	        }
	        
	       
	        if (userDao.findByUsername(user.getUsername()).isPresent()) {
	            throw new IllegalArgumentException("Username already exists");
	        }

	        userDao.save(user);
	    }

	    @Override
	    public Optional<User> findUserByUsername(String username) {
	        if (username == null || username.isEmpty()) {
	            return Optional.empty();
	        }
	        return userDao.findByUsername(username);
	    }
}
