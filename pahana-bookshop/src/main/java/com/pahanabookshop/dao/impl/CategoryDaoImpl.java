package com.pahanabookshop.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.CategoryDao;
import com.pahanabookshop.dao.ExceptionDao;
import com.pahanabookshop.model.Category;
import com.pahanabookshop.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

    private static final String INSERT = "INSERT INTO categories(name) VALUES (?)";
    private static final String SELECT_ONE = "SELECT * FROM categories WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM categories";
    private static final String UPDATE = "UPDATE categories SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM categories WHERE id=?";

    @Override
    public void save(Category category) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, category.getName());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                category.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    @Override
    public void update(Category category) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    @Override
    public Optional<Category> findById(int id) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try (Connection con = DBUtil.INSTANCE.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                list.add(map(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    /* Helper method to map ResultSet to Category */
    private Category map(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        return c;
    }
}
