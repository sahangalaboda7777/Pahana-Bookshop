package com.pahanabookshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.CustomerDao;
import com.pahanabookshop.dao.ExceptionDao;
import com.pahanabookshop.model.Customer;
import com.pahanabookshop.util.DBUtil;

public class CustomerDaoImpl implements CustomerDao {
	 private static final String INSERT =
		        "INSERT INTO customers(account_no,name,address,telephone,units) VALUES (?,?,?,?,?)";
		    private static final String SELECT_ONE =
		        "SELECT * FROM customers WHERE account_no=?";
		    private static final String SELECT_ALL =
		        "SELECT * FROM customers";
		    private static final String UPDATE =
		        "UPDATE customers SET name=?, address=?, telephone=?, units=? WHERE account_no=?";
		    private static final String DELETE =
		        "DELETE FROM customers WHERE account_no=?";
		    private static final String SELECT_LAST_ACCOUNT_NO =
		            "SELECT account_no FROM customers ORDER BY account_no DESC LIMIT 1";

		    @Override
		    public void save(Customer c) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(INSERT)) {

		            ps.setString(1, c.getAccountNo());
		            ps.setString(2, c.getName());
		            ps.setString(3, c.getAddress());
		            ps.setString(4, c.getTelephone());
		            ps.setInt   (5, c.getUnitsConsumed());
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public void update(Customer c) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(UPDATE)) {

		            ps.setString(1, c.getName());
		            ps.setString(2, c.getAddress());
		            ps.setString(3, c.getTelephone());
		            ps.setInt   (4, c.getUnitsConsumed());
		            ps.setString(5, c.getAccountNo());
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public void delete(String accountNo) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(DELETE)) {

		            ps.setString(1, accountNo);
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public Optional<Customer> findByAccountNo(String accountNo) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

		            ps.setString(1, accountNo);
		            ResultSet rs = ps.executeQuery();
		            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public List<Customer> findAll() {
		        List<Customer> list = new ArrayList<>();
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             Statement st = con.createStatement();
		             ResultSet rs = st.executeQuery(SELECT_ALL)) {

		            while (rs.next()) list.add(map(rs));
		            return list;

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }
		    
		    @Override
		    public String getLastAccountNo() {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             Statement st = con.createStatement();
		             ResultSet rs = st.executeQuery(SELECT_LAST_ACCOUNT_NO)) {
		            if (rs.next()) return rs.getString("account_no");
		            return null; // no records yet
		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    /* helper */
		    private Customer map(ResultSet rs) throws SQLException {
		        Customer c = new Customer();
		        c.setAccountNo   (rs.getString("account_no"));
		        c.setName        (rs.getString("name"));
		        c.setAddress     (rs.getString("address"));
		        c.setTelephone   (rs.getString("telephone"));
		        c.setUnitsConsumed(rs.getInt("units"));
		        return c;
		    }
}
