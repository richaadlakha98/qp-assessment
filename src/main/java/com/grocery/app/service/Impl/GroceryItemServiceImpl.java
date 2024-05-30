package com.grocery.app.service.Impl;

import com.grocery.app.entity.GroceryItems;
import com.grocery.app.repository.GroceryItemRepository;
import com.grocery.app.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroceryItemServiceImpl implements GroceryItemService {

    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItems> findAvailable() {
        return groceryItemRepository.findAllByDeletedFalse();
    }

    public List<GroceryItems> findAll() {
        return groceryItemRepository.findAll();
    }

    @Override
    public GroceryItems updateInventory(Long id, int stock) {
        GroceryItems groceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        groceryItem.setQuantity(stock);
        return save(groceryItem);
    }

    public Optional<GroceryItems> findById(Long id) {
        return groceryItemRepository.findById(id);
    }

    public GroceryItems save(GroceryItems groceryItems) {
        return groceryItemRepository.save(groceryItems);
    }

    public void deleteById(Long id) {
        Optional<GroceryItems> groceryItem = groceryItemRepository.findById(id);
        if (groceryItem.isEmpty()) {
            throw new IllegalArgumentException("Cant delete item as item not found");
        }
        groceryItem.get().setDeleted(true);
        save(groceryItem.get());
    }

    @Override
    public List<GroceryItems> add(List<GroceryItems> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cant add items as input is empty");
        }
        for (GroceryItems item : items) {
            GroceryItems groceryItems = new GroceryItems();
            groceryItems.setName(item.getName());
            groceryItems.setPrice(item.getPrice());
            groceryItems.setQuantity(item.getQuantity());
            save(groceryItems);
        }
        return items;
    }

    @Override
    public GroceryItems update(GroceryItems groceryItems) {
        Optional<GroceryItems> groceryItem = groceryItemRepository.findById(groceryItems.getId());
        if (groceryItem.isEmpty()) {
            throw new IllegalArgumentException("Cant update item as item not found");
        }
        groceryItem.get().setName(groceryItems.getName());
        groceryItem.get().setPrice(groceryItems.getPrice());
        groceryItem.get().setQuantity(groceryItems.getQuantity());
        save(groceryItem.get());
        return groceryItem.get();
    }
}
