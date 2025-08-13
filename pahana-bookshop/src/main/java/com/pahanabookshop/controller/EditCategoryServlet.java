package com.pahanabookshop.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CategoryDaoImpl;
import com.pahanabookshop.model.Category;
import com.pahanabookshop.service.CategoryService;
import com.pahanabookshop.service.impl.CategoryServiceImpl;

@WebServlet("/edit-category")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Optional<Category> optionalCategory = categoryService.findCategoryById(id);
            if (optionalCategory.isPresent()) {
                request.setAttribute("category", optionalCategory.get());
                request.getRequestDispatcher("/edit-category.jsp").forward(request, response);
            } else {
                response.sendRedirect("categories");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");

        try {
            int id = Integer.parseInt(idStr);
            Category category = new Category();
            category.setId(id);
            category.setName(name.trim());

            categoryService.updateCategory(category);
            response.sendRedirect("categories");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/edit-category.jsp?id=" + idStr).forward(request, response);
        }
    }
}
