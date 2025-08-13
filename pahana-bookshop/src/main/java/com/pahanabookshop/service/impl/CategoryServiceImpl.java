package com.pahanabookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.CategoryDao;
import com.pahanabookshop.model.Category;
import com.pahanabookshop.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	 private final CategoryDao categoryDao;

	    public CategoryServiceImpl(CategoryDao categoryDao) {
	        this.categoryDao = categoryDao;
	    }

	    @Override
	    public void addCategory(Category category) {
	        if (category == null) throw new IllegalArgumentException("Category cannot be null");
	        if (category.getName() == null || category.getName().isEmpty()) {
	            throw new IllegalArgumentException("Category name is required");
	        }
	        categoryDao.save(category);
	    }

	    @Override
	    public void updateCategory(Category category) {
	        if (category == null || category.getId() <= 0) {
	            throw new IllegalArgumentException("Invalid category or ID");
	        }
	        Optional<Category> existing = categoryDao.findById(category.getId());
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Category does not exist");
	        }
	        categoryDao.update(category);
	    }

	    @Override
	    public void deleteCategory(int id) {
	        if (id <= 0) throw new IllegalArgumentException("Invalid category ID");
	        Optional<Category> existing = categoryDao.findById(id);
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Category does not exist");
	        }
	        categoryDao.delete(id);
	    }

	    @Override
	    public Optional<Category> findCategoryById(int id) {
	        if (id <= 0) return Optional.empty();
	        return categoryDao.findById(id);
	    }

	    @Override
	    public List<Category> getAllCategories() {
	        return categoryDao.findAll();
	    }
}
