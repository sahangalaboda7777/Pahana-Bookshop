package com.pahanabookshop.service;

import java.util.Optional;

import com.pahanabookshop.model.User;

public interface UserService {
	void addUser(User user);
    Optional<User> findUserByUsername(String username);
}
