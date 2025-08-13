package com.pahanabookshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.ItemDaoImpl;
import com.pahanabookshop.model.Item;
import com.pahanabookshop.service.ItemService;
import com.pahanabookshop.service.impl.ItemServiceImpl;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {


	        List<Item> items = itemService.getAllItems();
	        request.setAttribute("items", items);

	        request.getRequestDispatcher("/item.jsp").forward(request, response);
	    }
}
