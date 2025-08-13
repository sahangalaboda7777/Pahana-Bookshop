package com.pahanabookshop.service;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Customer;

public interface CustomerService {
	 void registerCustomer(Customer customer);
	    void updateCustomer(Customer customer);
	    void deleteCustomer(String accountNo);
	    Optional<Customer> findCustomerByAccountNo(String accountNo);
	    List<Customer> getAllCustomers();
}
