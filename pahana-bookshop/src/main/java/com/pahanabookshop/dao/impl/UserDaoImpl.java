package com.pahanabookshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.pahanabookshop.dao.ExceptionDao;
import com.pahanabookshop.dao.UserDao;
import com.pahanabookshop.model.User;
import com.pahanabookshop.util.DBUtil;

public class UserDaoImpl implements UserDao {
	 private static final String INSERT =
		        "INSERT INTO users(username,password,role) VALUES (?,?,?)";
		    private static final String SELECT_ONE =
		        "SELECT * FROM users WHERE username=?";

		    @Override
		    public void save(User u) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(INSERT)) {

		            ps.setString(1, u.getUsername());
		            ps.setString(2, u.getPassword());  
		            ps.setString(3, u.getRole());
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public Optional<User> findByUsername(String username) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

		            ps.setString(1, username);
		            ResultSet rs = ps.executeQuery();
		            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    private User map(ResultSet rs) throws SQLException {
		        return new User(
		                rs.getString("username"),
		                rs.getString("password"),
		                rs.getString("role")
		        );
		    }
}
