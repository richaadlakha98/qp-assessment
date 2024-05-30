package com.grocery.app.service;

import com.grocery.app.entity.GroceryItems;

import java.util.List;
import java.util.Optional;


public interface GroceryItemService {

    List<GroceryItems> findAvailable();

    Optional<GroceryItems> findById(Long id);

    void deleteById(Long id);

    List<GroceryItems> add(List<GroceryItems> items);

    GroceryItems update(GroceryItems groceryItems);

    List<GroceryItems> findAll();

    GroceryItems updateInventory(Long id, int stock);
}
