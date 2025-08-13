package com.pahanabookshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CustomerDaoImpl;
import com.pahanabookshop.model.Customer;
import com.pahanabookshop.service.CustomerService;
import com.pahanabookshop.service.impl.CustomerServiceImpl;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        List<Customer> customers = customerService.getAllCustomers();

	        
	        // Attach customers to the request
	        request.setAttribute("customers", customers);

	        // Forward to JSP for display
	        request.getRequestDispatcher("/customer.jsp").forward(request, response);
	    }

}
