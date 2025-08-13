package com.pahanabookshop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.pahanabookshop.dao.ItemDao;
import com.pahanabookshop.model.Item;
import com.pahanabookshop.service.ItemService;

public class ItemServiceImpl implements ItemService {
	 private final ItemDao itemDao;

	    public ItemServiceImpl(ItemDao itemDao) {
	        this.itemDao = itemDao;
	    }

	    @Override
	    public void addItem(Item item) {
	        if (item == null) throw new IllegalArgumentException("Item cannot be null");
	        if (item.getTitle() == null || item.getTitle().isEmpty()) {
	            throw new IllegalArgumentException("Item title is required");
	        }
	        if (item.getUnitPrice() == null || item.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
	            throw new IllegalArgumentException("Unit price must be non-negative");
	        }
	        if (item.getStockQty() < 0) {
	            throw new IllegalArgumentException("Stock quantity cannot be negative");
	        }

	        itemDao.save(item);
	    }

	    @Override
	    public void updateItem(Item item) {
	        if (item == null || item.getId() == 0) {
	            throw new IllegalArgumentException("Invalid item or ID");
	        }
	        Optional<Item> existing = itemDao.findById(item.getId());
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Item does not exist");
	        }

	        itemDao.update(item);
	    }

	    @Override
	    public void deleteItem(int id) {
	        if (id <= 0) throw new IllegalArgumentException("Invalid item id");
	        Optional<Item> existing = itemDao.findById(id);
	        if (existing.isEmpty()) {
	            throw new IllegalArgumentException("Item does not exist");
	        }

	        itemDao.delete(id);
	    }

	    @Override
	    public Optional<Item> findItemById(int id) {
	        if (id <= 0) return Optional.empty();
	        return itemDao.findById(id);
	    }

	    @Override
	    public List<Item> getAllItems() {
	        return itemDao.findAll();
	    }
}
