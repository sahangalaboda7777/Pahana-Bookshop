package com.pahanabookshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CategoryDaoImpl;
import com.pahanabookshop.model.Category;
import com.pahanabookshop.service.CategoryService;
import com.pahanabookshop.service.impl.CategoryServiceImpl;

@WebServlet("/add-category")
public class AddCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Category name cannot be empty");
            request.getRequestDispatcher("/category.jsp").forward(request, response);
            return;
        }

        Category category = new Category();
        category.setName(name.trim());

        try {
            categoryService.addCategory(category);
            response.sendRedirect("categories");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/category.jsp").forward(request, response);
        }
    }
}
