package com.pahanabookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.CustomerDao;
import com.pahanabookshop.model.Customer;
import com.pahanabookshop.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	 private final CustomerDao customerDao;

	    public CustomerServiceImpl(CustomerDao customerDao) {
	        this.customerDao = customerDao;
	    }

	    @Override
	    public void registerCustomer(Customer customer) {
	        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
	        if (customer.getName() == null || customer.getName().isEmpty()) {
	            throw new IllegalArgumentException("Customer name is required");
	        }
	        if (customer.getTelephone() == null || customer.getTelephone().isEmpty()) {
	            throw new IllegalArgumentException("Telephone number is required");
	        }
	        if (customer.getUnitsConsumed() < 0) {
	            throw new IllegalArgumentException("Units consumed cannot be negative");
	        }

	        // Generate new account number
	        String newAccountNo = generateNextAccountNo();
	        customer.setAccountNo(newAccountNo);

	        customerDao.save(customer);
	    }

	    @Override
	    public void updateCustomer(Customer customer) {
	        if (customer == null || customer.getAccountNo() == null) {
	            throw new IllegalArgumentException("Invalid customer or account number");
	        }
	        Optional<Customer> existing = customerDao.findByAccountNo(customer.getAccountNo());
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Customer does not exist");
	        }

	        customerDao.update(customer);
	    }

	    @Override
	    public void deleteCustomer(String accountNo) {
	        if (accountNo == null || accountNo.isEmpty()) {
	            throw new IllegalArgumentException("Account number required");
	        }
	        Optional<Customer> existing = customerDao.findByAccountNo(accountNo);
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Customer does not exist");
	        }

	        customerDao.delete(accountNo);
	    }

	    @Override
	    public Optional<Customer> findCustomerByAccountNo(String accountNo) {
	        if (accountNo == null || accountNo.isEmpty()) {
	            return Optional.empty();
	        }
	        return customerDao.findByAccountNo(accountNo);
	    }

	    @Override
	    public List<Customer> getAllCustomers() {
	        return customerDao.findAll();
	    }

	    private String generateNextAccountNo() {
	        String last = customerDao.getLastAccountNo();  // e.g., C123
	        int next = 1;

	        if (last != null && last.matches("C\\d+")) {
	            next = Integer.parseInt(last.substring(1)) + 1;
	        }

	        return String.format("C%03d", next); // C001, C002, ..., C999
	    }
}
