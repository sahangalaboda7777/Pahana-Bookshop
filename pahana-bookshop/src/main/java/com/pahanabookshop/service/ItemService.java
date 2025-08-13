package com.pahanabookshop.service;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Item;

public interface ItemService {
	void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(int id);
    Optional<Item> findItemById(int id);
    List<Item> getAllItems();
}
