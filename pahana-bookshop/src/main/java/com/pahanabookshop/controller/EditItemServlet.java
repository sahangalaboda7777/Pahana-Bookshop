package com.pahanabookshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanabookshop.dao.impl.ItemDaoImpl;
import com.pahanabookshop.model.Item;
import com.pahanabookshop.service.ItemService;
import com.pahanabookshop.service.impl.ItemServiceImpl;

@WebServlet("/edit-item")
public class EditItemServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String itemIdStr = request.getParameter("itemId");

	        try {
	            int itemId = Integer.parseInt(itemIdStr);
	            Optional<Item> optionalItem = itemService.findItemById(itemId);

	            if (optionalItem.isPresent()) {
	                Item item = optionalItem.get();

	                request.setAttribute("item", item);
	                request.getRequestDispatcher("/edit-item.jsp").forward(request, response);
	            } else {
	                response.sendRedirect("item.jsp");
	            }

	        } catch (NumberFormatException e) {
	            response.sendRedirect("/item.jsp");
	        }
	    }


	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String itemId = request.getParameter("itemId");
	        String title = request.getParameter("title");
	        String unitPriceStr = request.getParameter("unitPrice");
	        String stockQtyStr = request.getParameter("stockQty"); 

	        try {
	            int id = Integer.parseInt(itemId);
	            BigDecimal unitPrice = new BigDecimal(unitPriceStr);
	            int stockQty = Integer.parseInt(stockQtyStr);

	            Item item = new Item();
	            item.setId(id);
	            item.setTitle(title);
	            item.setUnitPrice(unitPrice);
	            item.setStockQty(stockQty);

	            itemService.updateItem(item);
	            response.sendRedirect("items");

	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Invalid input for ID or Unit Price");
	            request.getRequestDispatcher("/edit-item.jsp?itemId=" + itemId).forward(request, response);
	        } catch (Exception e) {
	            request.setAttribute("error", e.getMessage());
	            request.getRequestDispatcher("/edit-item.jsp?itemId=" + itemId).forward(request, response);
	        }
	    }
}
