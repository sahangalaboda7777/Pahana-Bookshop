package com.pahanabookshop.service;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Bill;
import com.pahanabookshop.model.Customer;

public interface BillingService {
	 Bill generateBill(String accountNo, int unitsConsumed, String generatedBy);
	    Optional<Bill> findBillByBillNo(String billNo);
	    List<Bill> getBillsForCustomer(String accountNo);
	    Optional<Customer> findCustomer(String accountNo);
}
