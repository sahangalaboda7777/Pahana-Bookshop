package com.pahanabookshop.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pahanabookshop.dao.impl.UserDaoImpl;
import com.pahanabookshop.model.User;
import com.pahanabookshop.service.UserService;
import com.pahanabookshop.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        Optional<User> optionalUser = userService.findUserByUsername(username);

	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();

	            if (user.getPassword().equals(password)) {
	                // Store user in session
	                HttpSession session = request.getSession();
	                session.setAttribute("user", user);

	                // Redirect based on role
	                String role = user.getRole().toLowerCase();
	                switch (role) {
	                    case "cashier":
	                        request.getRequestDispatcher("/home.jsp").forward(request, response);
	                        return;
	                    case "admin":
	                        request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
	                        return;
	                    default:
	                        // Unknown role, return error
	                        request.setAttribute("error", "Unknown user role");
	                        request.getRequestDispatcher("/login.jsp").forward(request, response);
	                        return;
	                }
	            }
	        }

	        // Invalid login
	        request.setAttribute("error", "Invalid username or password");
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }
}
