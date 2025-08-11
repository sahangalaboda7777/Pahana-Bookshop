package com.pahanabookshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBUtil {
	
	INSTANCE;

	    private static final String URL = "jdbc:mysql://127.0.0.1:3306/pahana_bookshop?user=root";
	    private static final String USER = "root";
	    private static final String PWD = "root";  

	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	        } catch (ClassNotFoundException e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }

	    public Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PWD);
	    }

}


