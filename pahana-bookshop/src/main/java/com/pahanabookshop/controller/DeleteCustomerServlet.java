package com.pahanabookshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CustomerDaoImpl;
import com.pahanabookshop.service.CustomerService;
import com.pahanabookshop.service.impl.CustomerServiceImpl;

@WebServlet("/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");

        if (accountNo != null && !accountNo.isEmpty()) {
            try {
                customerService.deleteCustomer(accountNo);
            } catch (Exception e) {
                // Optional: handle exception, maybe set error attribute
            }
        }

        response.sendRedirect("customers");
    }
}
