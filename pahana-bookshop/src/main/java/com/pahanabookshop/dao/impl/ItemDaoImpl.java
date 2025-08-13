package com.pahanabookshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.ExceptionDao;
import com.pahanabookshop.dao.ItemDao;
import com.pahanabookshop.model.Item;
import com.pahanabookshop.util.DBUtil;

public class ItemDaoImpl implements ItemDao {
	 private static final String INSERT =
		        "INSERT INTO items(title,unit_price,stock_qty) VALUES (?,?,?)";
		    private static final String SELECT_ONE =
		        "SELECT * FROM items WHERE id=?";
		    private static final String SELECT_ALL =
		        "SELECT * FROM items";
		    private static final String UPDATE =
		        "UPDATE items SET title=?, unit_price=?, stock_qty=? WHERE id=?";
		    private static final String DELETE =
		        "DELETE FROM items WHERE id=?";

		    @Override
		    public void save(Item i) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(
		                     INSERT, Statement.RETURN_GENERATED_KEYS)) {

		            ps.setString(1, i.getTitle());
		            ps.setBigDecimal(2, i.getUnitPrice());
		            ps.setInt(3, i.getStockQty());
		            ps.executeUpdate();

		            ResultSet keys = ps.getGeneratedKeys();
		            if (keys.next()) i.setId(keys.getInt(1));

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public void update(Item i) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(UPDATE)) {

		            ps.setString(1, i.getTitle());
		            ps.setBigDecimal(2, i.getUnitPrice());
		            ps.setInt(3, i.getStockQty());
		            ps.setInt(4, i.getId());
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public void delete(int id) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(DELETE)) {

		            ps.setInt(1, id);
		            ps.executeUpdate();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public Optional<Item> findById(int id) {
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    @Override
		    public List<Item> findAll() {
		        List<Item> list = new ArrayList<>();
		        try (Connection con = DBUtil.INSTANCE.getConnection();
		             Statement st = con.createStatement();
		             ResultSet rs = st.executeQuery(SELECT_ALL)) {

		            while (rs.next()) list.add(map(rs));
		            return list;

		        } catch (SQLException e) { throw new ExceptionDao(e); }
		    }

		    /* helper */
		    private Item map(ResultSet rs) throws SQLException {
		        Item i = new Item();
		        i.setId(rs.getInt("id"));
		        i.setTitle(rs.getString("title"));
		        i.setUnitPrice(rs.getBigDecimal("unit_price"));
		        i.setStockQty(rs.getInt("stock_qty"));
		        return i;
		    }
}
