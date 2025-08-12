package com.pahanabookshop.dao;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Category;

public interface CategoryDao {
    void save(Category category);
    void update(Category category);
    void delete(int id);
    Optional<Category> findById(int id);
    List<Category> findAll();
}
