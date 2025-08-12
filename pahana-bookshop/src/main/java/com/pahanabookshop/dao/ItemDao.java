package com.pahanabookshop.dao;

import java.util.List;
import java.util.Optional;

import com.pahanabookshop.model.Item;

public interface ItemDao {
	void save(Item item);
    void update(Item item);
    void delete(int id);

    Optional<Item> findById(int id);
    List<Item> findAll();
}
