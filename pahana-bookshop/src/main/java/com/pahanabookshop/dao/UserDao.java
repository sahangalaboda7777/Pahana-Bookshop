package com.pahanabookshop.dao;

import java.util.Optional;
import com.pahanabookshop.model.User;

public interface UserDao {
	void save(User user);
    Optional<User> findByUsername(String username);
}
