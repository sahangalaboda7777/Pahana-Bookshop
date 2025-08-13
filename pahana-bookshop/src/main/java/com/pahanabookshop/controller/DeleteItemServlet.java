package com.pahanabookshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.ItemDaoImpl;
import com.pahanabookshop.service.ItemService;
import com.pahanabookshop.service.impl.ItemServiceImpl;

@WebServlet("/delete-item")
public class DeleteItemServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
		private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String itemId = request.getParameter("itemId");

	        if (itemId != null && !itemId.isEmpty()) {
	            try {
	                int id = Integer.parseInt(itemId);
	                itemService.deleteItem(id);
	            } catch (NumberFormatException e) {
	                
	            } catch (Exception e) {
	              
	            }
	        }

	        response.sendRedirect("items");
	    }
}
