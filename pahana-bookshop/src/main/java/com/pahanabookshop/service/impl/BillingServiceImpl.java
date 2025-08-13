package com.pahanabookshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pahanabookshop.dao.BillDao;
import com.pahanabookshop.dao.CustomerDao;
import com.pahanabookshop.model.Bill;
import com.pahanabookshop.model.Customer;
import com.pahanabookshop.service.BillingService;

public class BillingServiceImpl implements BillingService {
	private final BillDao billDao;
    private final CustomerDao customerDao;

    public BillingServiceImpl(BillDao billDao, CustomerDao customerDao) {
        this.billDao = billDao;
        this.customerDao = customerDao;
    }

    @Override
    public Bill generateBill(String accountNo, int unitsConsumed, String generatedBy) {
        if (accountNo == null || accountNo.isEmpty()) {
            throw new IllegalArgumentException("Account number is required");
        }
        if (unitsConsumed < 0) {
            throw new IllegalArgumentException("Units consumed cannot be negative");
        }
        if (generatedBy == null || generatedBy.isEmpty()) {
            throw new IllegalArgumentException("GeneratedBy user is required");
        }

        Optional<Customer> customerOpt = customerDao.findByAccountNo(accountNo);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        Customer customer = customerOpt.get();

        // Business logic for bill calculation 
        BigDecimal ratePerUnit = new BigDecimal("10");  
        BigDecimal amount = ratePerUnit.multiply(BigDecimal.valueOf(unitsConsumed));

        Bill bill = new Bill();
        bill.setBillNo(UUID.randomUUID().toString());
        bill.setAccountNo(accountNo);
        bill.setUnits(unitsConsumed);
        bill.setAmount(amount);
        bill.setGeneratedBy(generatedBy);
        bill.setIssuedAt(LocalDateTime.now());

        billDao.save(bill);

        return bill;
    }

    @Override
    public Optional<Bill> findBillByBillNo(String billNo) {
        if (billNo == null || billNo.isEmpty()) {
            return Optional.empty();
        }
        return billDao.findByBillNo(billNo);
    }

    @Override
    public List<Bill> getBillsForCustomer(String accountNo) {
        if (accountNo == null || accountNo.isEmpty()) {
            throw new IllegalArgumentException("Account number required");
        }
        return billDao.findAllForCustomer(accountNo);
    }
    
    @Override
    public Optional<Customer> findCustomer(String accountNo) {
        if (accountNo == null || accountNo.isEmpty()) {
            return Optional.empty();
        }
        return customerDao.findByAccountNo(accountNo);
    }
}
