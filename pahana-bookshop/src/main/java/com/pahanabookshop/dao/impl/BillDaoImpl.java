package com.pahanabookshop.dao.impl;

import java.sql.*;
import java.util.*;

import com.pahanabookshop.dao.ExceptionDao;
import com.pahanabookshop.model.Bill;
import com.pahanabookshop.util.DBUtil;
import com.pahanabookshop.dao.BillDao;

public class BillDaoImpl implements BillDao {
	  private static final String INSERT =
		        "INSERT INTO bills(bill_no,account_no,units,amount,generated_by,issued_at) VALUES (?,?,?,?,?,?)";
		    private static final String SELECT_ONE =
		        "SELECT * FROM bills WHERE bill_no=?";
		    private static final String SELECT_BY_CUSTOMER =
		        "SELECT * FROM bills WHERE account_no=? ORDER BY issued_at DESC";

		    @Override
		    public void save(Bill b) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(INSERT)) {

		            ps.setString(1, b.getBillNo());
		            ps.setString(2, b.getAccountNo());
		            ps.setInt   (3, b.getUnits());
		            ps.setBigDecimal(4, b.getAmount());
		            ps.setString(5, b.getGeneratedBy());
		            ps.setTimestamp(6, Timestamp.valueOf(b.getIssuedAt()));
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public Optional<Bill> findByBillNo(String billNo) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

		            ps.setString(1, billNo);
		            ResultSet rs = ps.executeQuery();
		            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public List<Bill> findAllForCustomer(String accountNo) {
		        List<Bill> list = new ArrayList<>();
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(SELECT_BY_CUSTOMER)) {

		            ps.setString(1, accountNo);
		            ResultSet rs = ps.executeQuery();
		            while (rs.next()) list.add(map(rs));
		            return list;

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    private Bill map(ResultSet rs) throws SQLException {
		        Bill b = new Bill();
		        b.setBillNo     (rs.getString("bill_no"));
		        b.setAccountNo  (rs.getString("account_no"));
		        b.setUnits      (rs.getInt("units"));
		        b.setAmount     (rs.getBigDecimal("amount"));
		        b.setGeneratedBy(rs.getString("generated_by"));
		        b.setIssuedAt   (rs.getTimestamp("issued_at").toLocalDateTime());
		        return b;
		    }
}
