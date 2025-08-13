package com.pahanabookshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CustomerDaoImpl;
import com.pahanabookshop.model.Customer;
import com.pahanabookshop.service.CustomerService;
import com.pahanabookshop.service.impl.CustomerServiceImpl;

@WebServlet("/add-customer")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String unitsParam = request.getParameter("units");

        try {
            // Input validation
            if (name == null || name.trim().isEmpty()
                    || telephone == null || telephone.trim().isEmpty()
                    || unitsParam == null || unitsParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Name, telephone, and units are required.");
            }

            int units;
            try {
                units = Integer.parseInt(unitsParam.trim());
                if (units < 0) {
                    throw new IllegalArgumentException("Units consumed cannot be negative.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Units must be a valid number.");
            }

            // Create customer object (without accountNo if it's auto-generated)
            Customer customer = new Customer();
            customer.setName(name.trim());
            customer.setAddress(address != null ? address.trim() : "");
            customer.setTelephone(telephone.trim());
            customer.setUnitsConsumed(units);

            customerService.registerCustomer(customer);
            response.sendRedirect("customers");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/add-customer.jsp").forward(request, response);
        }
    }
}
