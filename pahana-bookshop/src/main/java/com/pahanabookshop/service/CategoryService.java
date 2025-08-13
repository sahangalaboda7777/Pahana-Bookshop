package com.pahanabookshop.service;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Category;

public interface CategoryService {
	 void addCategory(Category category);
	    void updateCategory(Category category);
	    void deleteCategory(int id);
	    Optional<Category> findCategoryById(int id);
	    List<Category> getAllCategories();

}
