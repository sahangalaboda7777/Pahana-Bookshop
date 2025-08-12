package com.pahanabookshop.dao;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Bill;

public interface BillDao {
	void save(Bill bill);

    Optional<Bill> findByBillNo(String billNo);
    List<Bill> findAllForCustomer(String accountNo);
}
