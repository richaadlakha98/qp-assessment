package com.grocery.app.repository;

import com.grocery.app.entity.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItems, Long> {

    @Query(value = "select * from grocery_items where deleted = false", nativeQuery = true)
    List<GroceryItems> findAvailableItems();

    List<GroceryItems> findAllByDeletedFalse();
}
