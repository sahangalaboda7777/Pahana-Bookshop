package com.pahanabookshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.CategoryDaoImpl;
import com.pahanabookshop.service.CategoryService;
import com.pahanabookshop.service.impl.CategoryServiceImpl;

@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            // optional: log or handle exception
        }

        response.sendRedirect("categories");
    }
}
