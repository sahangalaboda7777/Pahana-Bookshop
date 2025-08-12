package com.pahanabookshop.dao;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Customer;

public interface CustomerDao {
	void save(Customer c);
    void update(Customer c);
    void delete(String accountNo);
    Optional<Customer> findByAccountNo(String accountNo);
    List<Customer> findAll();
	String getLastAccountNo();
}
